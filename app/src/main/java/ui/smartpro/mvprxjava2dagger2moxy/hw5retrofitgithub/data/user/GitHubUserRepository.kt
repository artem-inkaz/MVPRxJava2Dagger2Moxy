package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Path

interface GitHubUserRepository {

    fun getUsers(): Observable<List<GitHubUser>>

    fun getUserByLogin(userId: String): Maybe<GitHubUser>

    fun getUserListRepo(reposUrl: String): Single<List<GitHubUserRepoList>>
}