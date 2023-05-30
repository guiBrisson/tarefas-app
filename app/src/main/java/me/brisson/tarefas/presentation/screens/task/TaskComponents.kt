package me.brisson.tarefas.presentation.screens.task

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.tarefas.R
import me.brisson.tarefas.domain.utils.TaskDateHandler
import me.brisson.tarefas.presentation.design_system.theme.TarefasTheme


@Composable
fun TaskFields(
    modifier: Modifier = Modifier,
    dateTimeMillis: Long?,
    onDateChanged: (timeMillis: Long) -> Unit,
) {
    val context = LocalContext.current
    val dateHandler = TaskDateHandler(context, dateTimeMillis)
    val formattedDate = dateHandler.formattedDateAndTime()
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        DialogPickerDate(
            dateTimeMillis = dateTimeMillis,
            onDismissRequest = { showDatePicker = false },
            onDateChanged = onDateChanged
        )
    }

    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Icon(
                modifier = Modifier.size(14.dp),
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            )

            Text(
                text = "Due",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        }

        Row(
            modifier = Modifier
                .weight(4f)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = { showDatePicker = true }
                ),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (formattedDate.isNullOrEmpty()) {
                Text(
                    text = "set due date",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    style = MaterialTheme.typography.bodySmall,
                )
            } else {
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialogPickerDate(
    dateTimeMillis: Long? = null,
    onDismissRequest: () -> Unit,
    onDateChanged: (timeMillis: Long) -> Unit,
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = dateTimeMillis)

    val colors = DatePickerDefaults.colors(
        containerColor = Color(0xFF141414),
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        headlineContentColor = MaterialTheme.colorScheme.onSurface,
        weekdayContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
        selectedDayContainerColor = MaterialTheme.colorScheme.primary,
        selectedDayContentColor = MaterialTheme.colorScheme.onSurface,
        subheadContentColor = MaterialTheme.colorScheme.onSurface,
        dayInSelectionRangeContentColor = MaterialTheme.colorScheme.onSurface,
        dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.onSurface,
        yearContentColor = MaterialTheme.colorScheme.onSurface,
        selectedYearContainerColor = MaterialTheme.colorScheme.primary,
        selectedYearContentColor = MaterialTheme.colorScheme.onSurface,

    )

    DatePickerDialog(
        shape = RoundedCornerShape(4.dp),
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = {
                    onDismissRequest()
                    datePickerState.selectedDateMillis?.let { onDateChanged(it) }
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF202020),
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ),
            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            OutlinedButton(
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ),
                border = BorderStroke(width = 1.dp, color = Color(0xFF202020)),
                onClick = onDismissRequest
            ) {
                Text("Cancel")
            }
        },
        colors = colors,
    ) {
        DatePicker(
            state = datePickerState,
            colors = colors,
        )
    }
}

@Preview
@Composable
private fun PreviewTaskFields() {
    TarefasTheme {
        TaskFields(
            dateTimeMillis = System.currentTimeMillis(),
            onDateChanged = { },
        )
    }
}

@Preview
@Composable
private fun PreviewDialogPickerDate() {
    TarefasTheme {
        DialogPickerDate(onDismissRequest = { }, onDateChanged = { })
    }
}
