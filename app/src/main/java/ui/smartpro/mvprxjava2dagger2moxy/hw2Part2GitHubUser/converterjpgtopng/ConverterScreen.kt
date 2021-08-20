package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

object ConverterScreen: FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        ConverterFragment.newInstance()
}