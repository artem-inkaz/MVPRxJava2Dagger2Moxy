package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ConverterView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showOnBoarding()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onPositiveClick()
}