package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation

import android.os.Bundle
import android.widget.Toast
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpAppCompatActivity
import ui.smartpro.mvprxjava2dagger2moxy.cicerone.App.Navigation.navigatorHolder
import ui.smartpro.mvprxjava2dagger2moxy.cicerone.App.Navigation.router
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.network.NetworkState
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.network.NetworkStateObservable
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.users.UsersScreen
import java.util.concurrent.TimeUnit

class MainActivity : MvpAppCompatActivity() {

    private val navigator = AppNavigator(this, android.R.id.content)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState ?: router.newRootScreen(UsersScreen)

        val connect =
            NetworkStateObservable(this)
                .doOnNext { onNext(0, it) }
                .publish()

        connect.connect()

        disposables +=
            connect.delay(32L, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe { onNext(1, it) }
        disposables += connect.delay(16L, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe { onNext(2, it) }
        disposables += connect.delay(8L, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe { onNext(3, it) }
    }

    private fun onNext(no: Int, state: NetworkState) {
        Toast.makeText(this, "$no: NetworkState: $state", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }
}