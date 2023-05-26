package me.brisson.tarefas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.brisson.tarefas.data.database.dao.TaskDao
import me.brisson.tarefas.data.database.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TarefasDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}