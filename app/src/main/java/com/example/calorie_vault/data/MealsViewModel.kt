package com.example.calorie_vault.data

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealDao : MealDao
) : ViewModel() {

}