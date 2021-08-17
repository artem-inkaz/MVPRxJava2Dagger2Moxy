package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.user

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    @SerializedName("id") val id: String,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar: String,
    @SerializedName("repos_url") val repos_url: String,

)