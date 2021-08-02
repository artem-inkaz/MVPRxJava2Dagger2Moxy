package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import ui.smartpro.mvprxjava2dagger2moxy.databinding.FragmentUserBinding
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.model.GithubUser

class UserFragment : MvpAppCompatFragment() {

    // Для загрузки из MovieList
    private lateinit var userBundle: GithubUser

    companion object {
        private const val EXTRA_USER_ID = "extra_user_id"
        fun newInstance(githubUser: GithubUser): UserFragment {
            return UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_USER_ID, githubUser)
                }
            }
        }
    }

    val userFromFragment: GithubUser
        get() = requireArguments().getParcelable<GithubUser>(EXTRA_USER_ID)!!

    private var viewbinding: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            viewbinding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userBundle = arguments?.getParcelable<GithubUser>(EXTRA_USER_ID)!!
        userBundle.let {
            displayUser(it)
        }
    }

    private fun displayUser(githubUser: GithubUser) {
        with(viewbinding) {
            this?.userId?.text = githubUser.login
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewbinding = null
    }
}