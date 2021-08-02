package ui.smartpro.mvprxjava2dagger2moxy

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

interface CountersView: MvpView {

    /**
     * Показывает приветственное сообщение
     * для пользователя.
     */
    //OneExecutionStateStrategy — команда добавится в очередь и удалится после первого выполнения.
    @OneExecution
    fun showOnBoarding()

    /**
     * Показвает значение счетчика 1.
     * @param counter значение
     */
    // стратегия сохранения комманд
    //AddToEndSingleStrategy — добавит пришедшую команду в конец очереди команд.
    // Если команда такого типа уже есть в очереди, то действующая удалится.
    // В большинстве случаев подходит именно эта стратегия.
    //@AddToEndSingle - есть ещё такой алиас
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setButtonText1(text: String)

    /**
     * Показвает значение счетчика 2.
     * @param counter значение
     */
    //@AddToEndSingle - есть ещё такой алиас
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setButtonText2(text: String)

    /**
     * Показвает значение счетчика 3.
     * @param counter значение
     */
    //@AddToEndSingle - есть ещё такой алиас
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setButtonText3(text: String)

    /**
     * Показывает сообщение об изменении счетчика.
     */
    //выполнения.
    //SkipStrategy — команда не добавится в очередь и никак её (очередь) не изменит.
    @Skip
    fun showCounterMessage()
}