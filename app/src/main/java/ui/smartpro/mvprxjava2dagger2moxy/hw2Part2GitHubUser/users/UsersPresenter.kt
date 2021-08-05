package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user.GithubUser
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user.GithubUsersRepo
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.IUserListPresenter
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces.UserItemView
import ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.user.UserScreen

/**
 *  переносим сюда содержимое MainPresenter,
 *  поправив его для работы с UsersView и
 *  передав в него Router для навигации
 */
class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
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

    private val compositeDisposable = CompositeDisposable()
    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

    }

    private fun loadData() {
        Observable.fromCallable {
            val users = usersRepo.getUsers()
            usersListPresenter.users.addAll(users)
            usersListPresenter.itemClickListener = { userItemView ->
                router.navigateTo(UserScreen(users[userItemView.pos]).userID())
                Log.d("usersListPresenter", "${userItemView.pos}")
            }
        }
//            .filter{/* do something */ }
//            .flatMap{/* do something */ }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {/* do something */
                            viewState.updateList()
                          }
            .doOnDispose { /* do something */ }
            .doOnError { /* do something */ }
            .subscribe({
                Log.d("usersListPresenter", "Успешно")
            },
                {
                    Log.e("usersListPresenter", "Ошибка", it)
                }
            )
    }

    private fun loadData2() {
        val disposableGetUserID = Completable.fromAction {
            val users = usersRepo.getUsers()
            usersListPresenter.users.addAll(users)
            usersListPresenter.itemClickListener = { userItemView ->
                router.navigateTo(UserScreen(users[userItemView.pos]).userID())
                Log.d("usersListPresenter", "${userItemView.pos}")
            }
        }
//            .filter{/* do something */ }
//            .flatMap{/* do something */ }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { /* do something */ viewState.updateList()}
            .doOnDispose { /* do something */ }
            .doOnError { /* do something */ }
            .subscribe(
                {
                Log.d("usersListPresenter", "Успешно")
            },
                {
                    Log.e("usersListPresenter", "Ошибка")
                }
            )
        compositeDisposable.add(disposableGetUserID)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}