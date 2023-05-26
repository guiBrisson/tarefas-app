package me.brisson.tarefas.data.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.brisson.tarefas.data.database.TarefasDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesTarefasApp(
        @ApplicationContext context: Context
    ): TarefasDatabase = Room.databaseBuilder(
        context = context,
        klass = TarefasDatabase::class.java,
        name = "tarefas-database"
    ).build()
}
