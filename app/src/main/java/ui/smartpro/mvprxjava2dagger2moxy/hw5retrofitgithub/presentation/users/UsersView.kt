package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.users

import moxy.viewstate.strategy.alias.SingleState
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserViewModel
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.ScreenView

interface UsersView : ScreenView {

    /**
     * Показывает список пользователей.
     * @param users список пользователей
     */
    @SingleState
    fun showUsers(users: List<GitHubUserViewModel>)
}