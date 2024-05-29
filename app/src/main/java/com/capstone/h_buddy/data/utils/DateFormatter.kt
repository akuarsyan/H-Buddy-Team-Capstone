package com.capstone.h_buddy.data.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateFormatter {
    fun formatDate(currentDateString: String, targetTimeZone: String): String? {
        return try {
            // Format asli dari tanggal yang diterima (ISO 8601)
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")

            // Format keluaran yang diinginkan
            val outputFormat = SimpleDateFormat("dd MMM yyyy | HH:mm", Locale.getDefault())
            outputFormat.timeZone = TimeZone.getTimeZone(targetTimeZone)

            val date = inputFormat.parse(currentDateString)
            outputFormat.format(date)
        } catch (e: ParseException) {
            println("Error: Invalid date format. Please use ISO-8601 format.")
            null
        } catch (e: IllegalArgumentException) {
            println("Error: Invalid time zone.")
            null
        }
    }
}