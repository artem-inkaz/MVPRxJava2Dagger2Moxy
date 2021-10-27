package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.Database

@Module
class GitHubStorageModule {

    @Persisted
    @Provides
    fun provideGitHubDatabaseStorage(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "github.db")
//            .addMigrations(GitHub1to2Migration, GitHub2to3Migration)
            .build()

    @InMemory
    @Provides
    fun provideGitHubinMemoryStorage(context: Context): Database =
        Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .fallbackToDestructiveMigration()
            .build()
}