package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.user

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepository
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserViewModel
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepolist.UserRepoListScreen
import ui.smartpro.mvprxjava2dagger2moxy.scheduler.Schedulers

class UserPresenter(
    private val userLogin: String,
    private val router: Router,
    private val userRepository: GitHubUserRepository,
    private val schedulers: Schedulers
) : MvpPresenter<UserView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables +=
            userRepository
                .getUserByLogin(userLogin)
                .map(GitHubUserViewModel.Mapper::map)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    viewState::showUser,
                    viewState::showError
                )
    }

    fun displayUserRepos(user: GitHubUserViewModel) {
        router.navigateTo(UserRepoListScreen(user.repos_url))
    }

    override fun onDestroy() {
        disposables.clear()
    }
}