package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces



/**
 * интерфейс в Presenter экрана,
   после отдадим эту реализацию в качестве интерфейса в адаптер,
   чтобы делегировать ему всю логику:
 *
 * Здесь V представляет собой тип View для строки списка, а itemClickListener — функция,
 * принимающая на вход эту самую View. Таким образом, при обработке клика мы получаем от View позицию
 * и находим требуемый элемент.
 *
 * IListPresenter, сюда общие для всех списков вещи:
слушатель клика;
функция получения количества элементов;
функция наполнения View.
 */

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?

    //    fun onItemViewClick(githubUser: GithubUser)
    fun bindView(view: V)
    fun getCount(): Int
}