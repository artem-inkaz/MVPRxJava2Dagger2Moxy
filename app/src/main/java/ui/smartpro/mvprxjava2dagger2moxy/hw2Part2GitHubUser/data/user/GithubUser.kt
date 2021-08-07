package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * сущность, представляющая пользователя
 */

@Parcelize
data class GithubUser(
    val login: String
) : Parcelable
