package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.users

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.scheduler.Schedulers
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepository
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserViewModel
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.user.UserScreen

class UsersPresenter(
    private val userRepository: GitHubUserRepository,
    private val router: Router,
    private val schedulers: Schedulers
): MvpPresenter<UsersView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables +=
            userRepository
                .getUsers()
                .observeOn(schedulers.background())
                .map { users -> users.map(GitHubUserViewModel.Mapper::map) }
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    viewState::showUsers,
                    viewState::showError
                )
    }

    fun displayUser(user: GitHubUserViewModel) {
        router.navigateTo(UserScreen(user.login))
    }

    override fun onDestroy() {
        disposables.dispose()
    }
}