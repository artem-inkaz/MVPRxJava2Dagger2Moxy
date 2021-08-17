package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubRepository
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubUser

interface UserDataSource {

    fun getUsers(): Single<List<RoomGithubUser>>
    fun getUserByLogin(userId: String): Maybe<RoomGithubUser>

    fun getUserListRepo(reposUrl: String): Single<List<RoomGithubRepository>>
}