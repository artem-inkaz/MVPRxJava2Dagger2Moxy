package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUserscheduler.scheduler

import io.reactivex.Scheduler

interface Schedulers {

    fun background(): Scheduler
    fun main(): Scheduler

}