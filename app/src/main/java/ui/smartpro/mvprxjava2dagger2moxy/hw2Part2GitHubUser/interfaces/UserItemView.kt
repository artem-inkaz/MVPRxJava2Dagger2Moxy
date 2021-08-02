package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.interfaces

//интерфейс — IItemView, куда вынесли позицию элемента списка.
// Она понадобится для элемента любого списка в нашем приложении
interface UserItemView: IItemView {
    fun setLogin(text: String)
}