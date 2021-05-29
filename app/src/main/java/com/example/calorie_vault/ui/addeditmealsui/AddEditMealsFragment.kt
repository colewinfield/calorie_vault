package com.example.calorie_vault.ui.addeditmealsui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.calorie_vault.R
import com.example.calorie_vault.data.addeditmealdata.AddEditMealsViewModel
import com.example.calorie_vault.databinding.FragmentAddEditMealBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditMealsFragment : Fragment(R.layout.fragment_add_edit_meal) {

    private val viewModel: AddEditMealsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditMealBinding.bind(view)

        binding.apply {
            editMealTitle.setText(viewModel.mealName)
            editMealDescription.setText(viewModel.mealDescription)
            editCalories.setText(viewModel.calories.toString())
        }



    }
}