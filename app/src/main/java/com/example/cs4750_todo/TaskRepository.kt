package com.example.cs4750_todo

import androidx.lifecycle.LiveData

// Repository to abstract data operations
class TaskRepository(private val taskDao: TaskDao) {
    // LiveData of all tasks
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    // Insert a task
    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    // Update a task
    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    // Delete a task
    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}
