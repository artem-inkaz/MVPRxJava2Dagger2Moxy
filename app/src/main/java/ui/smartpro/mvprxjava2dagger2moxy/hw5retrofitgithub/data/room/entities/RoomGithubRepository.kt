package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Так как пользователь и репозиторий состоят в отношении «один ко многим», потребуется внешний ключ.
 * Для этого мы создали поле userId, которое связали с полем id в классе RoomGithubUser посредством
 * аргумента аннотации. В аргумент foreignKeys передаётся массив всех внешних ключей. Здесь он один
 * и в него мы передаём:
Класс внешней сущности entity.
Поле id, к которому привязываемся во внешней сущности в parentColumns.
Поле usedId, которое привязываем в текущей сущности в childColumns.
Поведение дочерних сущностей при удалении родительской в onDelete. В нашем случае мы передаём CASCADE,
чтобы дочерние сущности исчезали при удалении родительской, так как репозитории
без пользователя сюда не подходят.
 */
@Entity(
    tableName = "user_repos",
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class RoomGithubRepository(
    @PrimaryKey
    @SerializedName("repos_id")
    val id: Int,
    @ColumnInfo(name = "repos_name")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "repos_description")
    @SerializedName("description")
    val description: String?,
    @ColumnInfo(name = "repos_lang")
    @SerializedName("language")
    val language: String,
    @ColumnInfo(name = "fork_cpunt")
    @SerializedName("forks_count")
    val forks_count: Int,
    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    var userId: String
)