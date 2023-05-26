package me.brisson.tarefas.data.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.brisson.tarefas.data.database.TarefasDatabase
import me.brisson.tarefas.data.database.dao.TaskDao

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesTaskDao(
        database: TarefasDatabase
    ): TaskDao = database.taskDao()
}
