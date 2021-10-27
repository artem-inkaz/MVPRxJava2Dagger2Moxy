package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepolist

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.scheduler.Schedulers
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepository
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserRepoViewModel
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepodetail.UserRepoDetailScreen

class UserRepoListPresenter(
    private val repos_url: String,
    private val router: Router,
    private val userRepository: GitHubUserRepository,
    private val schedulers: Schedulers
) : MvpPresenter<UserRepoListView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables +=
            userRepository
                .getUserListRepo(repos_url)
                .observeOn(schedulers.background())
                .map { users -> users.map(GitHubUserRepoViewModel.Mapper::map) }
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    viewState::showUserRepo,
                    viewState::showError
                )
    }

    fun displayUserRepoDetail(detailRepo: GitHubUserRepoViewModel) {
        router.navigateTo(UserRepoDetailScreen(detailRepo.forks_count.toString()))
    }

    override fun onDestroy() {
        disposables.clear()
    }

}