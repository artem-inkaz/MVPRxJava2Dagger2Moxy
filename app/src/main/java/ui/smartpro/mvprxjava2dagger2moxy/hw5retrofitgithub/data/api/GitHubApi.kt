package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepoList

interface GitHubApi {

    @GET("/users")
    fun getUsers(@Query("since") since: Int? = null): Single<List<GitHubUser>>

    @GET("/users/{username}")
    fun getUserByLogin(@Path("username") login: String): Single<GitHubUser>

//    @GET("/users/{username}/repos")
//    fun getUserListRepo(@Path("username") login: String): Single<List<GitHubUserRepoList>>

    @GET
    fun getUserListRepo(@Url repos_url: String): Single<List<GitHubUserRepoList>>
}