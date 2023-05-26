package me.brisson.tarefas.presentation.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.tarefas.domain.model.Task
import me.brisson.tarefas.presentation.design_system.theme.TarefasTheme

@Composable
internal fun TaskRoute(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val task by viewModel.currentTask.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TaskScreen(
        modifier = modifier,
        task = task,
        showSaveButton = uiState.showSaveButton,
        saveTask = viewModel::saveTask,
        onBackClick = onBackClick,
    )

}

@Composable
internal fun TaskScreen(
    modifier: Modifier = Modifier,
    task: Task?,
    showSaveButton: Boolean,
    saveTask: (task: Task) -> Unit,
    onBackClick: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    LaunchedEffect(key1 = task) {
        name = task?.name ?: ""
        description = task?.description ?: ""
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }

            if (showSaveButton) {
                TextButton(onClick = {
                    val modifiedTask = task?.copy(
                        name = name,
                        description = description
                    ) ?: Task.new()

                    saveTask(modifiedTask)
                }) {
                    Text("save")
                }
            }

        }

        BasicTextField(
            modifier = Modifier.padding(vertical = 12.dp),
            value = name,
            onValueChange = { name = it },
            singleLine = true,
            textStyle = TextStyle(fontWeight = FontWeight.Medium, fontSize = 18.sp),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    innerTextField()
                    if (name.isEmpty()) {
                        Text(
                            text = "task name...",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                            ),
                        )
                    }
                }
            },
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp), thickness = 0.4.dp)

        Text(
            text = "Description",
            style = TextStyle(
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        )

        BasicTextField(
            modifier = Modifier.padding(vertical = 12.dp),
            value = description,
            onValueChange = { description = it },
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium,
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    innerTextField()
                    if (description.isEmpty()) {
                        Text(
                            text = "Description...",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            },
        )
    }
}

@Preview
@Composable
private fun PreviewTaskScreen() {
    val task = Task(
        id = "",
        name = "",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
    )

    TarefasTheme {
        TaskScreen(
            task = task,
            showSaveButton = true,
            saveTask = { },
            onBackClick = { },
        )
    }
}
