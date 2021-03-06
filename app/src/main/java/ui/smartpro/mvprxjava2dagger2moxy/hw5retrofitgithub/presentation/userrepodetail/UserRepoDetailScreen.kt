package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.presentation.userrepodetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserRepoDetailScreen(private val forkCount: String): FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        UserRepoDetailFragment.newInstance(forkCount)
}