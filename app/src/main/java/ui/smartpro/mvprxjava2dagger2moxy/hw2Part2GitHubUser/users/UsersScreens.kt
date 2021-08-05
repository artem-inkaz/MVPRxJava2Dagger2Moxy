package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.users

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.users.UsersFragment

object UsersScreens {
    fun GotoFragmentUsers(): Screen = FragmentScreen {
        UsersFragment.newInstance()
    }
}