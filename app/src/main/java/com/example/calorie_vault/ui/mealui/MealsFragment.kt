package com.example.calorie_vault.ui.mealui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calorie_vault.R
import com.example.calorie_vault.data.mealdata.Meal
import com.example.calorie_vault.data.mealdata.MealsAdapter
import com.example.calorie_vault.data.mealdata.MealsViewModel
import com.example.calorie_vault.databinding.FragmentMealsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MealsFragment : Fragment(R.layout.fragment_meals), MealsAdapter.OnItemClickListener {

    private val viewModel: MealsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMealsBinding.bind(view)

        val mealsAdapter = MealsAdapter(this)

        binding.apply {
            recyclerViewMeals.apply {
                adapter = mealsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)

                /**
                 * Data should not be put into the fragment; instead, use the viewModel
                 * which survives the configuration changes and keeps the data.
                 */
            }

            fabAddMeal.setOnClickListener {
                viewModel.onAddMealClicked()
            }

        }

        viewModel.meals.observe(viewLifecycleOwner) {
            /**
             * We pass the list if it has changed and then diffCallback calculates the
             * changes and then ListAdapter takes care of the visual update.
             */
            mealsAdapter.submitList(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.mealsEvents.collect { event ->
                when (event) {
                    is MealsViewModel.MealsEvent.NavigateToAddScreen -> {
                        val action = MealsFragmentDirections.actionMealsFragmentToAddEditMealsFragment()
                        findNavController().navigate(action)
                    }
                    is MealsViewModel.MealsEvent.NavigateToEditScreen -> {
                        val action = MealsFragmentDirections.actionMealsFragmentToAddEditMealsFragment(event.meal)
                        findNavController().navigate(action)
                    }
                }
            }
        }


    }

    override fun onEditClicked(meal: Meal) {
        viewModel.onEditClicked(meal)
    }


}