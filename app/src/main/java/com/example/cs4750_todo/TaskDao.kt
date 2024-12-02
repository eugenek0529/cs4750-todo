package com.example.cs4750_todo

import androidx.lifecycle.LiveData
import androidx.room.*

// DAO for Task
@Dao
interface TaskDao {
    // Get all tasks ordered by ID in descending order
    @Query("SELECT * FROM task_table ORDER BY id DESC")
    fun getAllTasks(): LiveData<List<Task>>

    // Insert new task
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    // Update existing task
    @Update
    suspend fun update(task: Task)

    // Delete a task
    @Delete
    suspend fun delete(task: Task)
}
