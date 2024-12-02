package com.example.cs4750_todo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cs4750_todo.databinding.NewTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDate
import java.util.Calendar

// Bottom sheet dialog for adding or editing a task
class NewTaskSheet(private val task: Task? = null) : BottomSheetDialogFragment() {

        // View binding for new_task.xml
        private lateinit var binding: NewTaskBinding

        // Reference to TaskViewModel
        private lateinit var taskViewModel: TaskViewModel

        // Stores selected due date
        private var selectedDate: LocalDate? = null

        override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?
        ): View? {
                // Inflate layout using view binding
                binding = NewTaskBinding.inflate(inflater, container, false)
                return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)

                // Initialize TaskViewModel
                taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

                // If editing an existing task populate the fields
                if (task != null) {
                        binding.taskTitle.text = "Edit Task"
                        binding.name.text = Editable.Factory.getInstance().newEditable(task.name)
                        binding.desc.text = Editable.Factory.getInstance().newEditable(task.description)
                        selectedDate = task.dueDate
                        binding.dateButton.text = selectedDate?.toString() ?: "Date"
                } else {
                        // For a new task
                        binding.taskTitle.text = "New Task"
                }

                // Set up the date button to show the DatePickerDialog
                binding.dateButton.setOnClickListener {
                        showDatePicker()
                }

                // Set up the save button
                binding.saveButton.setOnClickListener {
                        saveTask()
                }
        }

        // Function to display the date picker
        @SuppressLint("NewApi")
        private fun showDatePicker() {
                val calendar = Calendar.getInstance()

                // Show date in picker if date is already selected
                selectedDate?.let {
                        calendar.set(it.year, it.monthValue - 1, it.dayOfMonth)
                }

                // Create the DatePickerDialog
                val datePickerDialog = DatePickerDialog(
                        requireContext(),
                        { _, year, month, dayOfMonth ->
                                // Update the selected date
                                selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                                binding.dateButton.text = selectedDate.toString()
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                )

                // Set the minimum date to the current date
                datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
                datePickerDialog.show()
        }
        // Function to save task
        private fun saveTask() {
                val name = binding.name.text.toString().trim()
                val description = binding.desc.text.toString().trim()

                // Require user to enter a name
                if (name.isEmpty()) {
                        binding.name.error = "Please enter a task name"
                        return
                }

                if (task == null) {
                        // Create a new task
                        val newTask = Task(name, description, selectedDate)
                        taskViewModel.insert(newTask)
                } else {
                        // Update existing task
                        val updatedTask = task.copy(name = name, description = description, dueDate = selectedDate)
                        taskViewModel.update(updatedTask)
                }

                dismiss()
        }
}
