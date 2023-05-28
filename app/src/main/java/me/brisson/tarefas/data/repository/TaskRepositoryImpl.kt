package me.brisson.tarefas.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.brisson.tarefas.data.database.dao.TaskDao
import me.brisson.tarefas.data.database.model.TaskEntity
import me.brisson.tarefas.data.database.model.asExternalModel
import me.brisson.tarefas.domain.model.Task
import me.brisson.tarefas.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
): TaskRepository {
    override suspend fun upsertTask(task: Task) {
        taskDao.upsertEntity(task.asEntity())
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllEntities().map { it.map(TaskEntity::asExternalModel) }
    }

    override fun getTaskById(id: String): Flow<Task?> {
        return taskDao.getEntityById(id).map { it?.asExternalModel() }
    }

    override suspend fun deleteTask(task: Task) {
        return taskDao.deleteEntity(task.asEntity())
    }

    private fun Task.asEntity() = TaskEntity(
        id = id,
        name = name,
        dateTimeMillis = dateTimeMillis,
        description = description
    )
}
