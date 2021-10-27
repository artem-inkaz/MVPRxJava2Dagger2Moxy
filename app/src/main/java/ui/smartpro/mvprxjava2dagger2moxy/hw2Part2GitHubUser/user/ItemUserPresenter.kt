package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.user

import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user.GithubUser

class ItemUserPresenter(private val user: GithubUser) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        viewState.showUser(user)
    }
}