package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//сущность, представляющая пользователя
@Parcelize
data class GithubUser(
    val login: String
): Parcelable
