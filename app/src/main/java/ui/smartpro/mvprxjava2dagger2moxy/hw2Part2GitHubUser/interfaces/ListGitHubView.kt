package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * интерфейс презентера списка
 * Так как всё, что появится на экране — просто список, интерфейс включает всего два метода:
  */

@StateStrategyType(AddToEndSingleStrategy::class)
interface ListGitHubView : MvpView {

}