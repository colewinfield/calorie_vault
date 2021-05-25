package com.example.calorie_vault.data.addeditmealdata

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.calorie_vault.data.mealdata.MealDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditMealsViewModel @Inject constructor(
    private val mealDao: MealDao,
    private val state: SavedStateHandle
): ViewModel() {



}