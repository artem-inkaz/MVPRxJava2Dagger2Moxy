package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

import ui.smartpro.mvprxjava2dagger2moxy.cicerone.App
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.GitHubStorageFactory

object CacheUserDataSourceFactory {

    fun create(): CacheUserDataSource =
        CacheUserDataSourceImpl(
            GitHubStorageFactory.create(App.ContextHolder.context)
        )
}