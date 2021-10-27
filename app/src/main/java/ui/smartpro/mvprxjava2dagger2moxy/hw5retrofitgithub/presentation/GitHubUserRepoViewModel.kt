package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation

import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepoList

data class GitHubUserRepoViewModel(
    val name: String,
    val description: String,
    val language: String,
    val forks_count: Int
) {

    object Mapper {

        fun map(user: GitHubUserRepoList) =
            GitHubUserRepoViewModel(
                user.name,
                user.description!!,
                user.language,
                user.forks_count
            )

    }

}