package ca.tetervak.datedialogdemo.util

import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

private val dateFormatter =
    DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy")

fun formatDate(date: Date?): String? {
    return date?.toInstant()
        ?.atZone(ZoneId.systemDefault())
        ?.toLocalDate()
        ?.format(dateFormatter)
}
