package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.R.layout.view_user
import ui.smartpro.mvprxjava2dagger2moxy.cicerone.App.Navigation.router
import ui.smartpro.mvprxjava2dagger2moxy.databinding.ViewUserBinding
import ui.smartpro.mvprxjava2dagger2moxy.ext.arguments
import ui.smartpro.mvprxjava2dagger2moxy.ext.setStartDrawableCircleImageFromUri
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepositoryFactory
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserViewModel
import ui.smartpro.mvprxjava2dagger2moxy.scheduler.SchedulersFactory

class UserFragment: MvpAppCompatFragment(view_user), UserView {

    // Для загрузки из MovieList
    private lateinit var userBundle: GitHubUserViewModel
    companion object Factory {

        private const val ARG_USER_LOGIN = "arg_user_login"

        fun newInstance(userId: String): Fragment =
            UserFragment()
                .arguments(ARG_USER_LOGIN to userId)

    }

    private val userLogin: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    @Suppress("unused")
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            userLogin = userLogin,
            router = router,
            userRepository = GitHubUserRepositoryFactory.create(),
            schedulers = SchedulersFactory.create()
        )
    }

    private val viewBinding: ViewUserBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.userLogin.setOnClickListener {
            presenter.displayUserRepos(userBundle)
        }

    }

    override fun showUser(user: GitHubUserViewModel) {
        viewBinding.userLogin.setStartDrawableCircleImageFromUri(user.avatar)
        viewBinding.userLogin.text = user.login
        userBundle = user
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), "User Fragment", Toast.LENGTH_LONG).show()
    }
}