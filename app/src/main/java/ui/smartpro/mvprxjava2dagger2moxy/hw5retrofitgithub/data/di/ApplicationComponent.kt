package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.cicerone.App
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUserscheduler.scheduler.Schedulers
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di.modules.GitHubApiModule
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di.modules.GitHubStorageModule
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di.modules.UsersModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, UsersModule::class, GitHubApiModule::class, GitHubStorageModule::class])
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigationHolder(navigatorHolder: NavigatorHolder):Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): ApplicationComponent
    }

//    override fun inject(instance: App?) {
//        TODO("Not yet implemented")
//    }
}