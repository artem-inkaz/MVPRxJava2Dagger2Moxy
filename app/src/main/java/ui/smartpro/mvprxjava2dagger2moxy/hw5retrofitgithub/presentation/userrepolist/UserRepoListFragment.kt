package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepolist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.R.layout.view_user_repo_list
import ui.smartpro.mvprxjava2dagger2moxy.cicerone.App.Navigation.router
import ui.smartpro.mvprxjava2dagger2moxy.databinding.ViewUserRepoListBinding
import ui.smartpro.mvprxjava2dagger2moxy.ext.arguments
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepositoryFactory
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserRepoViewModel
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepolist.adapter.UserRepoAdapter
import ui.smartpro.mvprxjava2dagger2moxy.scheduler.SchedulersFactory

class UserRepoListFragment: MvpAppCompatFragment(view_user_repo_list), UserRepoListView, UserRepoAdapter.Delegate {

    companion object Factory {

        private const val ARG_USER_LOGIN = "arg_user_repos_list"

        fun newInstance(reposUrl: String): Fragment =
            UserRepoListFragment()
                .arguments(ARG_USER_LOGIN to reposUrl)

    }

    private val reposUrl: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    private val presenter: UserRepoListPresenter by moxyPresenter {
        UserRepoListPresenter(
            repos_url = reposUrl,
            router = router,
            userRepository = GitHubUserRepositoryFactory.create(),
            schedulers = SchedulersFactory.create()
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