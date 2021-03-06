package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUserscheduler.scheduler


import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class DefaultSchedulers : Schedulers {

    override fun background(): Scheduler = io.reactivex.schedulers.Schedulers.newThread()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}