package me.brisson.tarefas.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.brisson.tarefas.domain.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String?,
)

fun TaskEntity.asExternalModel() = Task(
    id = id,
    name = name,
    description = description
)
