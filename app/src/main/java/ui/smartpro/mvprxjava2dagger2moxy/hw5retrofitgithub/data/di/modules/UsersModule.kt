package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepository
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.GitHubUserRepositoryImpl
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource.CacheUserDataSource
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource.CacheUserDataSourceImpl
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource.CloudUserDataSource
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource.UserDataSource
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.MainActivity
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.user.UserFragment
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepodetail.UserRepoDetailFragment
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepolist.UserRepoListFragment
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.users.UsersFragment
import javax.inject.Singleton

@Module
interface UsersModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    fun bindUserRepoDetailFragment(): UserRepoDetailFragment

    @ContributesAndroidInjector
    fun bindUserRepoListFragment(): UserRepoListFragment

    @Singleton
    @Binds
    fun bindGitHubUserRepository(repository: GitHubUserRepositoryImpl): GitHubUserRepository
    @Binds
    fun bindUserDataSource(dataSource: CloudUserDataSource):UserDataSource
    @Binds
    fun bindCacheUserDataSource(cashUserDataSource: CacheUserDataSourceImpl):CacheUserDataSource

}