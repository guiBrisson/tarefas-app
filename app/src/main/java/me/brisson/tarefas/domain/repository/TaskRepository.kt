package me.brisson.tarefas.domain.repository

import kotlinx.coroutines.flow.Flow
import me.brisson.tarefas.domain.model.Task

interface TaskRepository {
    suspend fun upsertTask(task: Task)
    fun getAllTasks(): Flow<List<Task>>
    fun getTaskById(id: String): Flow<Task?>
    suspend fun deleteTask(task: Task)
}