package ui.smartpro.mvprxjava2dagger2moxy.hw2

import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.CountersModel2
import ui.smartpro.mvprxjava2dagger2moxy.CountersView

class CountersPresenter2(private val model: CountersModel2): MvpPresenter<CountersView>() {

    override fun onFirstViewAttach() {
        /*
         * Задаем начальное состояние счетчиков
         * при первом открытии экрана.
         */
        viewState.setButtonText1(CountersMapper.map(0))
        viewState.setButtonText2(CountersMapper.map(0))
        viewState.setButtonText3(CountersMapper.map(0))

        /*
         *
         */
        viewState.showOnBoarding()
    }

    fun incrementCounter1() =
        model.incrementCounter(counterId = 0)
            .let(CountersMapper::map)
            .let(viewState::setButtonText1)
            .also { viewState.showCounterMessage() }

    fun incrementCounter2() =
        model.incrementCounter(counterId = 1)
            .let(CountersMapper::map)
            .let(viewState::setButtonText2)
            .also { viewState.showCounterMessage() }

    fun incrementCounter3() =
        model.incrementCounter(counterId = 2)
            .let(CountersMapper::map)
            .let(viewState::setButtonText3)
            .also { viewState.showCounterMessage() }

}