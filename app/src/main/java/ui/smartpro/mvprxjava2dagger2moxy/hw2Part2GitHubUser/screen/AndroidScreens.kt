package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.screen

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.model.GithubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.ui.UserFragment
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.ui.UsersFragment

/**
 * будем объявлять экраны
 */
class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun userID(githubUser: GithubUser) =
        FragmentScreen { UserFragment.newInstance(githubUser) }

}