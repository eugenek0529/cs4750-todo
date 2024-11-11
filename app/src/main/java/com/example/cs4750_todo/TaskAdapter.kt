package com.example.cs4750_todo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4750_todo.databinding.TaskItemBinding
import java.time.format.DateTimeFormatter

// Adapter for RecyclerView to display tasks
class TaskAdapter(private val listener: TaskItemClickListener) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // List of tasks
    private var tasks = emptyList<Task>()

    // Formatter for displaying dates
    @SuppressLint("NewApi")
    private val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

    // ViewHolder class for task items
    inner class TaskViewHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Bind task data to views
        @SuppressLint("NewApi")
        fun bind(task: Task) {
            binding.name.text = task.name
            binding.description.text = task.description
            binding.dueDate.text = task.dueDate?.format(formatter) ?: "No Due Date"

            // Handle edit button click
            binding.editButton.setOnClickListener {
                listener.onEditTask(task)
            }

            // Handle delete button click
            binding.deleteButton.setOnClickListener {
                listener.onDeleteTask(task)
            }
        }
    }

    // Create new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    // Bind data to ViewHolder
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position]
        holder.bind(currentTask)
    }

    // Get item count
    override fun getItemCount(): Int {
        return tasks.size
    }

    // Update list of tasks
    fun setTasks(taskList: List<Task>) {
        tasks = taskList
        notifyDataSetChanged()
    }
}
