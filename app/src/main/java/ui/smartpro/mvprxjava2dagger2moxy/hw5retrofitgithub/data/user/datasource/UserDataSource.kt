package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepoList

interface UserDataSource {

    fun getUsers(): Single<List<GitHubUser>>
    fun getUserByLogin(userId: String): Maybe<GitHubUser>

    fun getUserListRepo(reposUrl: String): Single<List<GitHubUserRepoList>>
}