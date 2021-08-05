package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.user

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user.GithubUser

/**
 * будем объявлять экраны
 */
class UserScreen(private val githubUser: GithubUser) {
     fun userID():Screen =
        FragmentScreen { UserFragment.newInstance(githubUser) }

}