package me.brisson.tarefas.domain.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date


class TaskDateHandler(
    context: Context,
    private val dateInMillis: Long?,
) {
    private val currentLocale = context.resources.configuration.locales[0]

    fun getLocalDate(): LocalDate? {
        dateInMillis?.let {
            val instant = Instant.ofEpochMilli(it)
            return instant.atZone(ZoneId.systemDefault()).toLocalDate()
        }
        return null
    }

    fun getLocalTime(): LocalTime? {
        dateInMillis?.let {
            val instant = Instant.ofEpochMilli(it)
            val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            return localDateTime.toLocalTime()
        }
        return null
    }

    fun formattedDateAndTime(): DateResult {
        dateInMillis?.let {
            val date = Date(it)
            val formattedDate = SimpleDateFormat("dd, MMMM", currentLocale).format(date)
            val formattedTime = SimpleDateFormat("HH:mm", currentLocale).format(date)
            return DateResult(formattedDate, formattedTime)
        }
        return DateResult(null, null)
    }

    data class DateResult(
        val date: String?,
        val time: String?,
    )
}
