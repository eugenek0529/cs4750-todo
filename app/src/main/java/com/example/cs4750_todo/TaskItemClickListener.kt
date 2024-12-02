package com.example.cs4750_todo

// Interfance for task item clicks
interface TaskItemClickListener {
    // Called when a task is edited
    fun onEditTask(task: Task)

    // Called when a task is deleted
    fun onDeleteTask(task: Task)
}
