package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.api.GitHubApi
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.api.GitHubApiInterceptor
import javax.inject.Named

@Module
class GitHubApiModule {

    @Named("github_api")
    @Provides
    fun provideBaseUrlProd(): String = "https://api.github.com"

    @Named("github_api_test")
    @Provides
    fun provideBaseUrlTest(): String = "https://api-test.github.com"

    @Provides
    fun provideGitHubApi(@Named("github_api") baseUrl: String): GitHubApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(GitHubApiInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GitHubApi::class.java)

    private val gson: Gson =
        GsonBuilder()
            .create()
}