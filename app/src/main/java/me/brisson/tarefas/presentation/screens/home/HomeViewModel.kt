package me.brisson.tarefas.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import me.brisson.tarefas.domain.model.Task
import me.brisson.tarefas.domain.repository.TaskRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    taskRepository: TaskRepository
) : ViewModel() {
    private val tasks: StateFlow<List<Task>> = taskRepository.getAllTasks()
        .catch { t -> _uiState.update { it.copy(tasksLoading = false, tasksError = t) } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = combine(_uiState, tasks) { state, tasks ->
        state.copy(tasksLoading = false, tasks = tasks)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

}
