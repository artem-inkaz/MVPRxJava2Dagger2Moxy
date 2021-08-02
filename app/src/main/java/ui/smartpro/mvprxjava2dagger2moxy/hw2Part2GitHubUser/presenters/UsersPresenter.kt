package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.presenters

import android.util.Log
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.IUserListPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.UserItemView
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.UsersView
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.model.GithubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.repositories.GithubUsersRepo
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.screen.IScreens

/**
 *  переносим сюда содержимое MainPresenter,
 *  поправив его для работы с UsersView и
 *  передав в него Router для навигации
 */
class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router, val screens: IScreens) :
    MvpPresenter<UsersView>() {
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
    class UsersListPresenter : IUserListPresenter {
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
        //       usersListPresenter.itemClickListener = {userItemView ->
        //           val user = users[userItemView.pos]
//          router.navigateTo(screens.userID(userItemView.pos))
//            router.navigateTo(Screens.GotoFragmentUserId(users[userItemView.pos]))
//           Log.d("usersListPresenter","${userItemView.pos} ")
//        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        usersListPresenter.itemClickListener = { userItemView ->
            router.navigateTo(screens.userID(users[userItemView.pos]))
//      router.navigateTo(Screens.GotoFragmentUserId(users[userItemView.pos]))
//      Log.d("usersListPresenter","${userItemView.pos} ")
            Log.d("usersListPresenter", "${users[userItemView.pos]}")
        }
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}