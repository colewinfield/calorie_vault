package com.example.calorie_vault.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calorie_vault.R
import com.example.calorie_vault.data.MealsAdapter
import com.example.calorie_vault.data.MealsViewModel
import com.example.calorie_vault.databinding.FragmentMealsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsFragment : Fragment(R.layout.fragment_meals) {

    private val viewModel: MealsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMealsBinding.bind(view)

        val mealsAdapter = MealsAdapter()

        binding.apply {
            recyclerViewMeals.apply {
                adapter = mealsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)

                // data should not be put into the fragment; instead, use the viewModel
                // the viewModel survives configuration changes and keeps the data
                // also a Separation of Concerns
            }
        }

        viewModel.meals.observe(viewLifecycleOwner) {
            mealsAdapter.submitList(it) // we pass the new list if it has changed
            // and then diffCallback calculates the changes
            // and then ListAdapter takes care of the visual update
        }
    }



}