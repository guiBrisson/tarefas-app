package me.brisson.tarefas.presentation.screens.task

import androidx.compose.foundation.clickable
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
import me.brisson.tarefas.R
import me.brisson.tarefas.domain.utils.MyDateUtils
import me.brisson.tarefas.presentation.design_system.theme.TarefasTheme


@Composable
fun TaskFields(
    modifier: Modifier = Modifier,
    dateTimeMillis: Long,
) {
    val context = LocalContext.current
    val formattedDate = MyDateUtils.formattedDate(context, dateTimeMillis)


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

        Text(
            modifier = Modifier.weight(4f).clickable(onClick = { }),
            text = formattedDate,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Preview
@Composable
fun PreviewTaskFields() {
    TarefasTheme {
        TaskFields(
            dateTimeMillis = System.currentTimeMillis(),
        )
    }
}
