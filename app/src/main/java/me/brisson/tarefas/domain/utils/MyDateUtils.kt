package me.brisson.tarefas.domain.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date


object MyDateUtils {
    fun formattedDate(context: Context, dateTimeMillis: Long): String{
        val date = Date(dateTimeMillis)
        val format = SimpleDateFormat("dd, MMMM - HH:mm", getCurrentLocale(context))
        return format.format(date)
    }

    private fun getCurrentLocale(context: Context): Locale {
        return context.resources.configuration.locales[0]
    }
}