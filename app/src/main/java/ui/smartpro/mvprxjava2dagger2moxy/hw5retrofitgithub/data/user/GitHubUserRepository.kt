package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Path
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubRepository
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubUser

interface GitHubUserRepository {

    fun getUsers(): Observable<List<RoomGithubUser>>

    fun getUserByLogin(userId: String): Maybe<RoomGithubUser>

    fun getUserListRepo(reposUrl: String): Single<List<RoomGithubRepository>>
}