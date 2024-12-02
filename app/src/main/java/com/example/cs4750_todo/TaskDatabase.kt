package com.example.cs4750_todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// Define Room database with entities and version
@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)  // Register the Converters class
abstract class TaskDatabase : RoomDatabase() {

    // Provide access to Dao
    abstract fun taskDao(): TaskDao

    companion object {
        // Prevents multiple instances
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        // Function to get database instance
        fun getDatabase(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
