package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface ScreenView : MvpView {

    @SingleState
    fun showError(error: Throwable)

}