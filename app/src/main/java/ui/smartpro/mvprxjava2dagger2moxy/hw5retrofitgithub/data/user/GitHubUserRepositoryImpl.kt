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

//        cache.getUsers()
//            .flatMap { users ->
//                if (users.isEmpty()) {
//                    cloud.getUsers()
//                        .flatMap(cache::retain)
//                } else {
//                    Single.just(users)
//                }
//            }
            //.map { users -> users.map { it.copy(login = it.login.lowercase()) } }

    override fun getUserByLogin(userId: String): Maybe<RoomGithubUser> =
        cache.getUserByLogin(userId)
            .switchIfEmpty(cloud.getUserByLogin(userId))

    override fun getUserListRepo(userId: String): Single<List<RoomGithubRepository>> =
        cloud.getUserListRepo(userId)

//            .subscribeOn(Schedulers.io())

//    override fun getUserListRepo(userId: String): Maybe<List<GitHubUserRepoList>> =
//        Observable.merge(
//        cache.getUserListRepo(userId)
//        cloud.getUserListRepo(userId).flatMap(cache::retainRepo).toObservable()
//        )
//        cache.getUserListRepo(userId)
//        .switchIfEmpty(cloud.getUserListRepo(userId))
}