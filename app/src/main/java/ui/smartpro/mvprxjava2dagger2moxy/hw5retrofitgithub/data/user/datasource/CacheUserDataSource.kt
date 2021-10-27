package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

import io.reactivex.Single
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubUser

interface CacheUserDataSource : UserDataSource {

    fun retain(users: List<RoomGithubUser>): Single<List<RoomGithubUser>>
    fun retain(user: RoomGithubUser): Single<RoomGithubUser>
}