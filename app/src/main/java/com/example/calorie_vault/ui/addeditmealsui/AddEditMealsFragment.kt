package com.example.calorie_vault.ui.addeditmealsui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.calorie_vault.R
import com.example.calorie_vault.databinding.FragmentAddEditMealBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddEditMealsFragment : Fragment(R.layout.fragment_add_edit_meal) {

    private val viewModel: AddEditMealsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditMealBinding.bind(view)

        binding.apply {
            editMealTitle.setText(viewModel.mealName)
//            editCalories.setText(viewModel.calories.toString())

            editMealTitle.addTextChangedListener {
                viewModel.mealName = it.toString()
            }


            fabAddEditSubmit.setOnClickListener {
                // TODO:
                viewModel.onSaveClick()
            }



        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditChannel.collect {

            }
        }



    }
}