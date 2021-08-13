package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.user

import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.GitHubUserViewModel

interface ClickGitHubUser {

    /**
     * Событие наступает при выборе
     * пользователя из списка.
     * @param user пользователь
     */
    fun onUserClick(user: GitHubUserViewModel)
}