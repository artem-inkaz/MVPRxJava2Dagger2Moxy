package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubRepository
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource.CacheUserDataSource
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource.UserDataSource

class GitHubUserRepositoryImpl(
    private val cloud: UserDataSource,
    private val cache: CacheUserDataSource
) : GitHubUserRepository {

    override fun getUsers(): Observable<List<RoomGithubUser>> =
        Observable.merge(
            cache.getUsers().toObservable(),
            cloud.getUsers().flatMap(cache::retain).toObservable()
        )
    override fun getUserByLogin(userId: String): Maybe<RoomGithubUser> =
        cache.getUserByLogin(userId)
            .switchIfEmpty(cloud.getUserByLogin(userId))

    override fun getUserListRepo(userId: String): Single<List<RoomGithubRepository>> =
        cloud.getUserListRepo(userId)
}