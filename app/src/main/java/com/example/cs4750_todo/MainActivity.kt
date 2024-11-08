package com.example.cs4750_todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cs4750_todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the "Add Task" button click listener
        binding.newTaskButton.setOnClickListener {
            // Open NewTaskSheet as a bottom sheet dialog
            val newTaskSheet = NewTaskSheet()
            newTaskSheet.show(supportFragmentManager, "newTaskTag")
        }
    }
}
