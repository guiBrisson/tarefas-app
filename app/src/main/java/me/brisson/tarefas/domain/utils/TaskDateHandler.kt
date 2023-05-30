package me.brisson.tarefas.domain.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date


class TaskDateHandler(
    context: Context,
    private val dateInMillis: Long?,
) {
    private val currentLocale = context.resources.configuration.locales[0]

    fun formattedDateAndTime(): String? {
        dateInMillis?.let {
            val date = Date(it)
            return SimpleDateFormat("dd, MMMM", currentLocale).format(date)
        }
        return null
    }

}
