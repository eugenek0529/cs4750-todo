package com.example.cs4750_todo

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Converts LocalDate to String and converts String to LocalDate
class Converters {
    @SuppressLint("NewApi")
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    // Convert LocalDate to String for storing in database
    @SuppressLint("NewApi")
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(formatter)
    }

    // Convert String back to LocalDate when retrieving from database
    @SuppressLint("NewApi")
    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it, formatter) }
    }
}
