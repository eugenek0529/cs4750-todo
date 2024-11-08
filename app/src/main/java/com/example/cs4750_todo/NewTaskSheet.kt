package com.example.cs4750_todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cs4750_todo.databinding.NewTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewTaskSheet : BottomSheetDialogFragment() {

private lateinit var binding: NewTaskBinding

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        binding = NewTaskBinding.inflate(inflater, container, false)
        return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)

                // Set up any required UI elements or listeners here
                binding.saveButton.setOnClickListener {
                        // Handle save button click - placeholder for now
                        dismiss()
                }
        }
}
