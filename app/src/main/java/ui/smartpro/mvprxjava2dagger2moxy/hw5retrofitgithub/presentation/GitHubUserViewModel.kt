package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation

import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUser

data class GitHubUserViewModel(
    val login: String,
    val avatar: String,
    val repos_url: String
) {

    object Mapper {

        fun map(user: GitHubUser) =
            GitHubUserViewModel(
                user.login,
                user.avatar,
                user.repos_url
            )
    }

}