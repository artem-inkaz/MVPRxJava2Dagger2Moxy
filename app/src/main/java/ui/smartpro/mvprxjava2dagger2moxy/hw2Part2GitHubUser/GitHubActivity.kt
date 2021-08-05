package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ui.smartpro.mvprxjava2dagger2moxy.R
import ui.smartpro.mvprxjava2dagger2moxy.databinding.GithubActivityBinding
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.cicerone.App.Navigation.navigatorHolder
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.cicerone.App.Navigation.router
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.BackButtonListener
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.ListGitHubView
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.presenters.MainPresenter

class GitHubActivity : MvpAppCompatActivity(), ListGitHubView {

    val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter { MainPresenter(router) }
    private var viewbinding: GithubActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = GithubActivityBinding.inflate(layoutInflater)
        setContentView(viewbinding?.root)
    }

    /**
     * Используется именно onResumeFragments, а не onResume.
     * Это делается потому, что в момент onResume некоторые фрагменты Activity
     * могут ещё не достичь своих onResume. Иногда это приводит к состоянию,
     * в котором запрещены транзакции фрагментов, а попытки навигации могут привести
     * к IllegalStateException. Во время реализации onResumeFragments
     * все фрагменты уже восстановлены и находятся в правильном состоянии.
     */

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    /**
     * Навигатор отсоединяется в onPause и присоединяется в onResumeFragments,
     * чтобы при переходе в другой Activity, который тоже может иметь свой навигатор,
     * вызовы передавались навигатору нового открытого Activity.
     * Новый открытый Activity также присоединится при запуске и отсоединится в onPause и т. д.
     */

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    /**
     * при нажатии кнопки «Назад» проходим по всем фрагментам в стеке и, встретив первый,
     * реализующий интерфейс, вызываем у него функцию backPressed().
     * Такой подход к обработке переходов вверх по стеку — стандартная практика при работе с фрагментами.
     *
     * Если оттуда возвращается true, значит, событие поглощено,
     * и никаких действий предпринимать не требуется. А если наоборот — сообщаем презентеру
     * о необходимости обработки нажатия
     */

    override fun onBackPressed() {

        /**
         * Класс SupportAppNavigator — часть Cicerone. Именно внутри него происходит
         * вся работа с FragmentManager в зависимости от отправляемых нами навигационных
         * команд.
         */
        supportFragmentManager
            .fragments
            .forEach {
                if (it is BackButtonListener && it.backPressed()) {
                    return
                }
            }
        presenter.backClicked()
    }
}