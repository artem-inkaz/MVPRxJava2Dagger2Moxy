package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

import io.reactivex.Single
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUser

interface CacheUserDataSource : UserDataSource {

    fun retain(users: List<RoomGithubUser>): Single<List<RoomGithubUser>>
    fun retain(user: RoomGithubUser): Single<RoomGithubUser>
//    fun retainRepo(userRepo: List<GitHubUserRepoList>): Observable<List<GitHubUserRepoList>>
//    fun retainRepo(userRepoFork: GitHubUserRepoList): Single<GitHubUserRepoList>
}