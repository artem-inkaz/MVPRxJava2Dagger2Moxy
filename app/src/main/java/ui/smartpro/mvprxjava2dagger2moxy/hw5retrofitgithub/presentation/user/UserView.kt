package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.user

import moxy.viewstate.strategy.alias.SingleState
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserViewModel
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.ScreenView

interface UserView : ScreenView {

    /**
     * Показывает информацию о пользователе.
     * @param user пользователь
     */
    @SingleState
    fun showUser(user: GitHubUserViewModel)
}