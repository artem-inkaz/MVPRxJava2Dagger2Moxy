package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepodetail

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepository
import ui.smartpro.mvprxjava2dagger2moxy.scheduler.Schedulers

class UserRepoDetailPresenter(
    private val forkCount: Int,
    private val userRepository: GitHubUserRepository,
    private val schedulers: Schedulers
) : MvpPresenter<UserRepoDetailView>() {

//    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
//        disposables +=

//                forkCount
//                .observeOn(schedulers.main())
//                .subscribeOn(schedulers.background())
//                .subscribe(
//                    viewState::showUser,
//                    viewState::showError
//                )
    }

    override fun onDestroy() {
//        disposables.clear()
    }

}