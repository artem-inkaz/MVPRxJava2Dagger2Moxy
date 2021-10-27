package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepolist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserRepoListScreen(private val reposUrl: String): FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        UserRepoListFragment.newInstance(reposUrl)
}