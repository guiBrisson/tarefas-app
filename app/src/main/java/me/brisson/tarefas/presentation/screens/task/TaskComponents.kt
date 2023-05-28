package me.brisson.tarefas.presentation.screens.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import me.brisson.tarefas.R
import me.brisson.tarefas.domain.utils.TaskDateHandler
import me.brisson.tarefas.presentation.design_system.theme.TarefasTheme
import java.time.ZoneId


@Composable
fun TaskFields(
    modifier: Modifier = Modifier,
    dateTimeMillis: Long?,
    onDateChanged: (timeMillis: Long) -> Unit,
) {
    val context = LocalContext.current
    val dateHandler = TaskDateHandler(context, dateTimeMillis)
    val (formattedDate, formattedTime) = dateHandler.formattedDateAndTime()
    val calendarState = rememberUseCaseState()

    DateTimeDialog(
        state = calendarState,
        selection = DateTimeSelection.DateTime(
            selectedDate = dateHandler.getLocalDate(),
            selectedTime = dateHandler.getLocalTime(),
        ) { date ->
            val instant = date.atZone(ZoneId.systemDefault()).toInstant()
            val milliseconds = instant.toEpochMilli()
            onDateChanged(milliseconds)
        },
        config = DateTimeConfig(locale = context.resources.configuration.locales[0])
    )

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
                    onClick = { calendarState.show() }
                ),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = formattedDate ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            if (!formattedDate.isNullOrEmpty() && !formattedTime.isNullOrEmpty()) {
                Text(text = "•", color = MaterialTheme.colorScheme.onBackground)
            } else {
                Text(
                    text = "set due date",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Text(
                text = formattedTime ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

    }
}

@Preview
@Composable
fun PreviewTaskFields() {
    TarefasTheme {
        TaskFields(
            dateTimeMillis = System.currentTimeMillis(),
            onDateChanged = { },
        )
    }
}
