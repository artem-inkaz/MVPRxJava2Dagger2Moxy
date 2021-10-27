package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user.datasource

object CacheUserDataSourceFactory {

    fun create(): CacheUserDataSource = CacheUserDataSourceImpl()
}