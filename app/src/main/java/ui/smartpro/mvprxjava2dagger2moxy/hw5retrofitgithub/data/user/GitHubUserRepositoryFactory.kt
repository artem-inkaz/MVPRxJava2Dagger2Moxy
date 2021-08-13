package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user

import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource.CacheUserDataSourceFactory
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource.UserDataSourceFactory

/**
 * Пока нет DI на основе Dagger2 мы решаем проблему
 * по старинке используя фабрики.
 */
object GitHubUserRepositoryFactory {

    fun create(): GitHubUserRepository =
        GitHubUserRepositoryImpl(
            UserDataSourceFactory.create(),
            CacheUserDataSourceFactory.create()
        )

}