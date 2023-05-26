package me.brisson.tarefas.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import me.brisson.tarefas.data.database.model.TaskEntity

@Dao
interface TaskDao {

    @Upsert
    suspend fun upsertEntity(task: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun getAllEntities(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id =:id")
    fun getEntityById(id: String): Flow<TaskEntity?>

    @Delete
    suspend fun deleteEntity(task: TaskEntity)
}