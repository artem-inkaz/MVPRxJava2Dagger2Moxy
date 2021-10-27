package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng

import moxy.MvpPresenter

class ConverterPresenter :MvpPresenter<ConverterView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun showDialog(){
        viewState::onPositiveClick
    }
}