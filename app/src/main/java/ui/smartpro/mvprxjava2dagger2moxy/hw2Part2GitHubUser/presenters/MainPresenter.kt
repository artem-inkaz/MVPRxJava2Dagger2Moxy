package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.presenters

import android.util.Log
import android.widget.Toast
import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.IUserListPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.ListGitHubView
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.UserItemView
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.model.GithubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.repositories.GithubUsersRepo

class MainPresenter(val usersRepo: GithubUsersRepo): MvpPresenter<ListGitHubView>() {

    /**
     * Мы реализовали интерфейс IUserListPresenter классом UsersListPresenter,
     * где и содержатся данные и логика по наполнению View.
     * Сюда же делегируется получение количества элементов списка через getCount().
     * В остальном всё просто:
    -при первом присоединении View вызываем метод init(), в котором напишем все операции по инициализации View;
    -затем получаем данные из репозитория;
    -отдаём их презентеру списка;
    -командуем View обновить список
     */

    class UsersListPresenter: IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = {userItemView ->
            userItemView.pos
            Log.d("usersListPresenter","${userItemView.pos}")
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

}