package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.databinding.FragmentUserBinding
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user.GithubUser

class UserFragment : MvpAppCompatFragment(), UserView {

    companion object {
        private const val EXTRA_USER_ID = "extra_user_id"
        fun newInstance(githubUser: GithubUser): UserFragment {
            return UserFragment().apply {
                arguments = Bundle().also {
                    it.putParcelable(EXTRA_USER_ID, githubUser)
                }
            }
        }
    }

    private val userFromUsersFragment by lazy {
        arguments?.getParcelable<GithubUser>(EXTRA_USER_ID) as GithubUser
    }

    private var viewbinding: FragmentUserBinding? = null

    @Suppress("unused")
    private val presenter by moxyPresenter { ItemUserPresenter(userFromUsersFragment) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            viewbinding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        viewbinding = null
    }

    override fun showUser(githubUser: GithubUser) {
        viewbinding?.userId?.text = githubUser.login
    }
}