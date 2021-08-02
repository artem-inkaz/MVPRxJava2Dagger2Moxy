package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.databinding.FragmentUsersBinding
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.adapter.UsersRVAdapter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.ListGitHubView
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.presenters.MainPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.repositories.GithubUsersRepo

class GitHubActivity: MvpAppCompatActivity(),ListGitHubView  {
    private val  presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }
    private var adapter: UsersRVAdapter? = null

    private var viewbinding: FragmentUsersBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = FragmentUsersBinding.inflate(layoutInflater)
        setContentView(viewbinding?.root)
    }

    /**
     *  В функции init() инициализируем адаптер и передаём туда Presenter списка
     */
    override fun init() {
        viewbinding?.rvUsers?.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        viewbinding?.rvUsers?.adapter = adapter
    }

    /**
     * в функции updateList() командуем адаптеру обновить список.
     */
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}