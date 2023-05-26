package me.brisson.tarefas.presentation.screens.home

import me.brisson.tarefas.domain.model.Task

data class HomeUiState(
    val tasksLoading: Boolean = true,
    val tasks: List<Task> = emptyList(),
    val tasksError: Throwable? = null,
)
