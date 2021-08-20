package ui.smartpro.mvprxjava2dagger2moxy.cicerone

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di.DaggerApplicationComponent
import ui.smartpro.mvprxjava2dagger2moxy.scheduler.DefaultSchedulers

class App:DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withNavigationHolder(cicerone.getNavigatorHolder())
                withRouter(cicerone.router)
                withSchedulers(DefaultSchedulers())
            }
            .build()

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {  }
    }

}