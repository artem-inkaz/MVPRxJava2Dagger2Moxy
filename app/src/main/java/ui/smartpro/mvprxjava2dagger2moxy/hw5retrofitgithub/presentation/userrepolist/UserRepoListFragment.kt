package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepolist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.R.layout.view_user_repo_list
import ui.smartpro.mvprxjava2dagger2moxy.databinding.ViewUserRepoListBinding
import ui.smartpro.mvprxjava2dagger2moxy.ext.arguments
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepository
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserRepoViewModel
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.abs.AbsFragment
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepolist.adapter.UserRepoAdapter
import ui.smartpro.mvprxjava2dagger2moxy.scheduler.Schedulers
import javax.inject.Inject

class UserRepoListFragment: AbsFragment(view_user_repo_list), UserRepoListView, UserRepoAdapter.Delegate {

    companion object Factory {

        private const val ARG_USER_LOGIN = "arg_user_repos_list"

        fun newInstance(reposUrl: String): Fragment =
            UserRepoListFragment()
                .arguments(ARG_USER_LOGIN to reposUrl)

    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var gitHubUserRepository: GitHubUserRepository

    private val reposUrl: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    private val presenter: UserRepoListPresenter by moxyPresenter {
        UserRepoListPresenter(
            repos_url = reposUrl,
            router = router,
            userRepository = gitHubUserRepository,
            schedulers = schedulers
        )
    }

    private val viewBinding: ViewUserRepoListBinding by viewBinding()
    private val userRepoAdapter = UserRepoAdapter(delegate = this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.users.adapter = userRepoAdapter
    }

    override fun showUserRepo(user: List<GitHubUserRepoViewModel>) {
        userRepoAdapter.submitList(user)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), "Какая то ошибка", Toast.LENGTH_LONG).show()
    }

    override fun onUserPickedRepo(userRepo: GitHubUserRepoViewModel) {
        presenter.displayUserRepoDetail(userRepo)
    }
}