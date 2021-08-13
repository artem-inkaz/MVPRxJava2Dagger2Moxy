package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.api.GitHubApiFactory

object UserDataSourceFactory {

    fun create(): UserDataSource = CloudUserDataSource(GitHubApiFactory.create())
}