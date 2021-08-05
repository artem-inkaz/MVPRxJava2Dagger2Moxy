package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

//репозиторий с фиктивными данными
class GithubUsersRepo {
    private val compositeDisposable = CompositeDisposable()

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers(): List<GithubUser> {
        return repositories
    }

//    fun getUsers(): Observable<List<GithubUser>> {
//        return Observable.create { emitter ->
//            emitter.onNext(repositories)
//            emitter.onError(Throwable("Данных нет"))
//            emitter.onComplete()
//        }
//    }


}
