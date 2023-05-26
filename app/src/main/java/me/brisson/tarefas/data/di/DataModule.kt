package me.brisson.tarefas.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.brisson.tarefas.data.repository.TaskRepositoryImpl
import me.brisson.tarefas.domain.repository.TaskRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsTaskRepository(
        taskRepository: TaskRepositoryImpl
    ): TaskRepository

}
