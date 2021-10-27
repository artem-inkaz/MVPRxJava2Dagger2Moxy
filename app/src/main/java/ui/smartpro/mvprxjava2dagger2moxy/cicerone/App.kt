package ui.smartpro.mvprxjava2dagger2moxy.cicerone

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import io.reactivex.plugins.RxJavaPlugins

class App:Application() {

    object ContextHolder {

        lateinit var context: Context

    }

    companion object Navigation{

        /**
         * Временно до даггера положим это тут
         */

        private val cicerone: Cicerone<Router> by lazy {
            Cicerone.create()
        }
        val navigatorHolder get() = cicerone.getNavigatorHolder()
        val router get() = cicerone.router
   }

    override fun onCreate() {
        super.onCreate()
        ContextHolder.context = applicationContext
        RxJavaPlugins.setErrorHandler {  }
    }

}