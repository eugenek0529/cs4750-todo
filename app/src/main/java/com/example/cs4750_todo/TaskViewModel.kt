package com.example.cs4750_todo

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

// Manages UI related data
class TaskViewModel(application: Application) : AndroidViewModel(application) {

    // Reference to repository
    private val repository: TaskRepository

    // LiveData of all tasks
    val allTasks: LiveData<List<Task>>

    init {
        // Initialize DAO and repository
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks
    }

    // Insert a new task
    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    // Update a task
    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    // Delete a task
    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }
}
