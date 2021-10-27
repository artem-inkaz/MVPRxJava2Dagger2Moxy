package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user

import io.reactivex.rxjava3.disposables.CompositeDisposable
/**
 * репозиторий с фиктивными данными
 */
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
