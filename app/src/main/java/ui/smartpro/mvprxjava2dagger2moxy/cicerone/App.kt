package ui.smartpro.mvprxjava2dagger2moxy.cicerone

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App:Application() {

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

}