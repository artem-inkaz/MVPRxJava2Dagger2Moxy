package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.api.GitHubApi
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepoList

class CloudUserDataSource(private val gitHubApi: GitHubApi) : UserDataSource {

    override fun getUsers(): Single<List<RoomGithubUser>> =
        gitHubApi.getUsers()

    override fun getUserByLogin(userId: String): Maybe<RoomGithubUser> =
        gitHubApi.getUserByLogin(userId)
            .toMaybe()

//    override fun getUserListRepo(reposUrl: String): Single<List<GitHubUserRepoList>> =
//        gitHubApi.getUserListRepo(reposUrl)

    override fun getUserListRepo(reposUrl: String) =
        gitHubApi.getUserListRepo(reposUrl).subscribeOn(Schedulers.io())

}