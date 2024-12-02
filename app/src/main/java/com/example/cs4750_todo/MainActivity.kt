package com.example.cs4750_todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs4750_todo.databinding.ActivityMainBinding

// Implement TaskItemClickListener to handle task interactions
class MainActivity : AppCompatActivity(), TaskItemClickListener {

    // View binding for activity_main.xml
    private lateinit var binding: ActivityMainBinding

    // Get TaskViewModelInstance
    private val taskViewModel: TaskViewModel by viewModels()

    // Adapter for RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView adapter
        adapter = TaskAdapter(this)
        binding.todoListRecyclerView.adapter = adapter
        binding.todoListRecyclerView.layoutManager = LinearLayoutManager(this)

        // Observe changes in the task list and update the RecyclerView
        taskViewModel.allTasks.observe(this) { tasks ->
            tasks?.let {
                adapter.setTasks(it)
            }
        }

        // Add Task button opens NewTaskSheet
        binding.newTaskButton.setOnClickListener {
            // Open NewTaskSheet as a bottom sheet dialog
            val newTaskSheet = NewTaskSheet()
            newTaskSheet.show(supportFragmentManager, "newTaskTag")
        }
    }

    // Handle edit task action
    override fun onEditTask(task: Task) {
        val editTaskSheet = NewTaskSheet(task)
        editTaskSheet.show(supportFragmentManager, "editTaskTag")
    }

    // Handle task delete action
    override fun onDeleteTask(task: Task) {
        taskViewModel.delete(task)
    }
}
