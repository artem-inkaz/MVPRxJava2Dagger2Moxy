package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.dao.RepositoryDao
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.dao.UserDao
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubRepository
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubUser

/**
 * Перечисляем сущности в аннотации, наследуемся от RoomDatabase и перечисляем DAO
 * в виде абстрактных полей. Остальной код — для временного Singletone, пока в проект не добавится DI.
 */
@androidx.room.Database(
    exportSchema = false,
    entities = [RoomGithubUser::class, RoomGithubRepository::class],
    version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}