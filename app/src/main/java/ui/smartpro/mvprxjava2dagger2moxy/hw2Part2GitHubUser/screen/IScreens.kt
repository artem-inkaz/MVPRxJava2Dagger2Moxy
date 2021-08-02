package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.screen

import com.github.terrakok.cicerone.Screen
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.model.GithubUser

interface IScreens {
    fun users(): Screen
    fun userID(githubUser: GithubUser): Screen
}