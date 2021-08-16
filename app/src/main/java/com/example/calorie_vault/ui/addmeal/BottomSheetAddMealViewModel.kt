package com.example.calorie_vault.ui.addmeal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.calorie_vault.data.mealdata.MealDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomSheetAddMealViewModel @Inject constructor(
    private val mealDao: MealDao,
    private val state: SavedStateHandle
) : ViewModel() {




}
