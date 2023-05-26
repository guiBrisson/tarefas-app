package me.brisson.tarefas.presentation.screens.task

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.brisson.tarefas.domain.model.Task
import me.brisson.tarefas.domain.repository.TaskRepository
import me.brisson.tarefas.presentation.navigation.AppNavigationArgs
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val taskRepository: TaskRepository,
): ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val currentTask = savedStateHandle.getStateFlow<String?>(
        key = AppNavigationArgs.task_id_args,
        initialValue = null
    ).flatMapLatest { taskId ->
        if(taskId == null) {
            flowOf(null)
        } else {
            taskRepository.getTaskById(taskId)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    fun saveTask(task: Task) {
        viewModelScope.launch {
            taskRepository.upsertTask(task)
        }
    }

}
