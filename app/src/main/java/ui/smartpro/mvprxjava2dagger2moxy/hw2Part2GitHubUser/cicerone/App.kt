package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.cicerone

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App:Application() {

    companion object{
        lateinit var instance: App
    }
    //Временно до даггера положим это тут
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}