package ui.smartpro.mvprxjava2dagger2moxy

class CountersModel2(private val counters: MutableList<Int> = mutableListOf(0, 0, 0)) {

    fun incrementCounter(counterId: Int) = ++counters[counterId]
}