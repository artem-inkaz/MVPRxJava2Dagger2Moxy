package ui.smartpro.mvprxjava2dagger2moxy


class MainPresenter(val view: MainView) {
    val model = CountersModel()

    //Архитектурная ошибка. В качестве практического задания - исправить
    fun counterClick(id: Int){
        when(id){
            0 -> {
                val nextValue = model.next(0)
                view.setButtonText1(nextValue.toString())
            }
            1 -> {
                val nextValue = model.next(1)
                view.setButtonText2(nextValue.toString())
            }
            2 -> {
                val nextValue = model.next(2)
                view.setButtonText3(nextValue.toString())
            }
        }
    }


}
