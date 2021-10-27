package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room

import android.content.Context
import androidx.room.Room

object GitHubStorageFactory {

    fun create(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "github.db")
//            .addMigrations(GitHub1to2Migration, GitHub2to3Migration)
            .build()

    fun createInMemory(context: Context): Database =
        Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .fallbackToDestructiveMigration()
            .build()
}