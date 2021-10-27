package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.SingleState
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user.GithubUser

@StateStrategyType(AddToEndSingleStrategy:: class)
interface UserView: MvpView {

    /**
     * Показывает информацию о пользователе.
     * @param user пользователь
     */
    @SingleState
    fun showUser(githubUser: GithubUser)
}