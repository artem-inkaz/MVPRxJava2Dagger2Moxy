package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 *  переносим сюда содержимое из MainView
 */
// интерфейс презентера списка
// Так как всё, что появится на экране — просто список, интерфейс включает всего два метода:
@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {

    /**
     * для первичной инициализации списка, который мы будем вызывать при присоединении View к Presenter;
     */
    fun init()

    /**
     * для обновления содержимого списка
     */
    fun updateList()
}