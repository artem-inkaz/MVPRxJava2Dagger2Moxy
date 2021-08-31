package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di.modules.InMemory
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.Database
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubRepository
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubUser
import javax.inject.Inject

class CacheUserDataSourceImpl @Inject constructor(
    @InMemory private val database: Database
    ) : CacheUserDataSource {

    override fun getUsers(): Single<List<RoomGithubUser>> =
        database
            .userDao
            .fetchUsers()

    override fun getUserByLogin(userId: String): Maybe<RoomGithubUser> =
        database
            .userDao
            .fetchUserByLogin(userId)
            .toMaybe()

    override fun getUserListRepo(reposUrl: String): Single<List<RoomGithubRepository>> =
        database
            .repositoryDao
            .getUserListRepo(reposUrl)

    override fun retain(users: List<RoomGithubUser>): Single<List<RoomGithubUser>> =
        database
            .userDao
            .retain(users)
            .andThen(getUsers())

    override fun retain(user: RoomGithubUser): Single<RoomGithubUser> =
        database
            .userDao
            .retain(user)
            .andThen(getUserByLogin(user.id))
            .toSingle()
}