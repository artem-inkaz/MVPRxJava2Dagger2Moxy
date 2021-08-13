package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user

import com.google.gson.annotations.SerializedName

data class GitHubUserRepoList(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("language")
    val language: String,
    @SerializedName("forks_count")
    val forks_count: Int
)
