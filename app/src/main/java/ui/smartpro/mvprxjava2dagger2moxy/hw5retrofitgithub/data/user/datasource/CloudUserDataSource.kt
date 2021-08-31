package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.api.GitHubApi
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di.modules.InMemory
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.Database
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubUser
import javax.inject.Inject

class CloudUserDataSource @Inject constructor(
    private val gitHubApi: GitHubApi,
    @InMemory private val database: Database
    ) : UserDataSource {

    override fun getUsers(): Single<List<RoomGithubUser>> =
        gitHubApi.getUsers()

    override fun getUserByLogin(userId: String): Maybe<RoomGithubUser> =
        gitHubApi.getUserByLogin(userId)
            .toMaybe()

    override fun getUserListRepo(reposUrl: String) =
        gitHubApi.getUserListRepo(reposUrl).subscribeOn(Schedulers.io())

}