package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.users

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreen: FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        UsersFragment.newInstance()

}