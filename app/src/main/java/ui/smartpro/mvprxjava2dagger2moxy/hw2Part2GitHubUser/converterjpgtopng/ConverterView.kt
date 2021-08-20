package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ConverterView : ScreenView {

    @AddToEndSingle
    fun showContent(uri: Uri?)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showOnBoarding()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onPositiveClick()
}