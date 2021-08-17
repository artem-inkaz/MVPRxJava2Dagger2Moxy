package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepolist

import moxy.viewstate.strategy.alias.SingleState
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserRepoViewModel
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.ScreenView

interface UserRepoListView : ScreenView {

    /**
     * Показывает информацию о пользователе.
     * @param user пользователь
     */
    @SingleState
    fun showUserRepo(user: List<GitHubUserRepoViewModel>)

}