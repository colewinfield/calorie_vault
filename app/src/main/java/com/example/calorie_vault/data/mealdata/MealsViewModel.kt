package com.example.calorie_vault.data.mealdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealDao : MealDao
) : ViewModel() {


    // whenever the table updates, new list of meals through this flow.
    // viewModel should never have a reference to the fragment to avoid memory leaks
    val meals = mealDao.getMeals().asLiveData()



    fun onAddMealClicked() {
        TODO("Not yet implemented")
    }

}