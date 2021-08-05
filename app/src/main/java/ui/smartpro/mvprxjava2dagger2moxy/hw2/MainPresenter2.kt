package ui.smartpro.mvprxjava2dagger2moxy

import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2.CountersMapper

// объявляем Moxy
class MainPresenter2(val model: CountersModel2) : MvpPresenter<CountersView>() {

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


    fun counterClick1() {
        model.incrementCounter(counterId = 0)
            .let { CountersMapper::map }
            .let { viewState::setButtonText1 }
            .also { viewState.showCounterMessage() }

    }

    fun counterClick2() {
        model.incrementCounter(counterId = 1)
            .let { CountersMapper::map }
            .let { viewState::setButtonText2 }
            .also { viewState.showCounterMessage() }
    }


    fun counterClick3() {
        model.incrementCounter(counterId = 2)
            .let { CountersMapper::map }
            .let { viewState::setButtonText3 }
            .also { viewState.showCounterMessage() }
    }
}