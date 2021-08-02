package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.presenters

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.ListGitHubView
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.screen.IScreens

/**
 * Здесь нет ничего, кроме замены экрана с пустоты, на экран пользователей во время старта и
 * обработки команды «Назад».
 */
class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<ListGitHubView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}