package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepoList

class CacheUserDataSourceImpl : CacheUserDataSource {

    private val cache = mutableListOf<GitHubUser>()
    private val cacheRepoList = mutableListOf<GitHubUserRepoList>()

    override fun getUsers(): Single<List<GitHubUser>> =
        Single.just(cache)

    override fun getUserByLogin(userId: String): Maybe<GitHubUser> =
        cache.firstOrNull { user -> user.login.contentEquals(userId) }
            ?.let { user -> Maybe.just(user) }
            ?: Maybe.empty()

    override fun getUserListRepo(reposUrl: String): Single<List<GitHubUserRepoList>> =
        Single.just(cacheRepoList)

    override fun retain(users: List<GitHubUser>): Single<List<GitHubUser>> =
        Single.fromCallable {
            cache.clear()
            cache.addAll(users)
            cache
        }

    override fun retain(user: GitHubUser): Single<GitHubUser> =
        Single.fromCallable { user }
}