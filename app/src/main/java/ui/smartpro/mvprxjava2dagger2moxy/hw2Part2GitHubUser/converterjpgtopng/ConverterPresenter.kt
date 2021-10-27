package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng

import android.net.Uri
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUserscheduler.scheduler.Schedulers
import java.util.concurrent.TimeUnit

class ConverterPresenter(
    private val converter: Converter,
    private val schedulers: Schedulers
) :MvpPresenter<ConverterView>() {

    private val disposables = CompositeDisposable()

    fun convert(uri: Uri) {
        viewState.showContent(uri)
        viewState.showOnBoarding()

        disposables +=
            converter
                .convert(uri)
                .delay(3, TimeUnit.SECONDS)
                .cache()
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    viewState::showContent,
                    viewState::showError
                )
    }

    fun cancel() {
        viewState.showContent(null)
        disposables.clear()
    }

    override fun onDestroy() = disposables.clear()

    fun showDialog(){
        viewState::onPositiveClick
    }
}