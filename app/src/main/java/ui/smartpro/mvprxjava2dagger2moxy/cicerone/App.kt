package ui.smartpro.mvprxjava2dagger2moxy.cicerone

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di.DaggerApplicationComponent

class App:DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .build()

    companion object Navigation{

        /**
         *         Временно до даггера положим это тут
         */

        private val cicerone: Cicerone<Router> by lazy {
            Cicerone.create()
        }
        val navigatorHolder get() = cicerone.getNavigatorHolder()
        val router get() = cicerone.router
   }

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {  }
    }

}