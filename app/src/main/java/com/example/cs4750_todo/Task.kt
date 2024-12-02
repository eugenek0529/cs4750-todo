package com.example.cs4750_todo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

// Define the entity for the Room database
@Entity(tableName = "task_table")
data class Task(
    var name: String,           // Task name
    var description: String,    // Task description
    var dueDate: LocalDate?,    // Task due date
    @PrimaryKey(autoGenerate = true) val id: Int = 0    // Primary key ID
)
