package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.data.user

/**
 * репозиторий с фиктивными данными
 */
class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}
