package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.presenters

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.users.UsersScreens
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.ListGitHubView

/**
 * Здесь нет ничего, кроме замены экрана с пустоты, на экран пользователей во время старта и
 * обработки команды «Назад».
 */
class MainPresenter(private val router: Router) : MvpPresenter<ListGitHubView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(UsersScreens.GotoFragmentUsers())
    }

    fun backClicked() = router.exit()

}