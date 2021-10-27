package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.users

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ui.smartpro.mvprxjava2dagger2moxy.R.layout.view_users
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.cicerone.App.Navigation.router
import ui.smartpro.mvprxjava2dagger2moxy.databinding.ViewUsersBinding
import ui.smartpro.mvprxjava2dagger2moxy.ext.arguments
import ui.smartpro.mvprxjava2dagger2moxy.scheduler.SchedulersFactory
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepositoryFactory
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserViewModel
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.users.adapter.UsersAdapter

class UsersFragment: MvpAppCompatFragment(view_users), UsersView, UsersAdapter.Delegate {

    companion object {

        fun newInstance(): Fragment =
            UsersFragment()
                .arguments()

    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            userRepository = GitHubUserRepositoryFactory.create(),
            router = router,
            schedulers = SchedulersFactory.create()
        )
    }

    private val viewBinding: ViewUsersBinding by viewBinding()
    private val usersAdapter = UsersAdapter(delegate = this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.users.adapter = usersAdapter
    }

    override fun showUsers(users: List<GitHubUserViewModel>) {
        usersAdapter.submitList(users)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun onUserPicked(user: GitHubUserViewModel) =
        presenter.displayUser(user)

}