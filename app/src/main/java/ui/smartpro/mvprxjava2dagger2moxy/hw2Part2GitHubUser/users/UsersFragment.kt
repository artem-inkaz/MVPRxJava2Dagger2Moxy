package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.databinding.FragmentUsersBinding
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.users.adapter.UsersRVAdapter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.cicerone.App.Navigation.router
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.BackButtonListener
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user.GithubUsersRepo

/**
 * переносим содержимое из GitHubActivity с правками
 */
class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        private const val EXTRA_USER_ID = "extra_user_id"
        fun newInstance() = UsersFragment()

    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), router)
    }

    private var adapter: UsersRVAdapter? = null
    private var viewbinding: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            viewbinding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        viewbinding = null
    }

    /**
     *  В функции init() инициализируем адаптер и передаём туда Presenter списка
     */
    override fun init() {
        viewbinding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        viewbinding?.rvUsers?.adapter = adapter
    }

    /**
     * в функции updateList() командуем адаптеру обновить список.
     */
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}