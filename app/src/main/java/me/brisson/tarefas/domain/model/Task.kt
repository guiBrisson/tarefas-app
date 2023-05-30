package me.brisson.tarefas.domain.model

import java.util.UUID

data class Task(
    val id: String,
    var name: String,
    var dateTimeMillis: Long?,
    var description: String?
) {
    companion object {
        fun new() = Task(
            id = UUID.randomUUID().toString(),
            name = "",
            dateTimeMillis = null,
            description = null
        )
    }
}
