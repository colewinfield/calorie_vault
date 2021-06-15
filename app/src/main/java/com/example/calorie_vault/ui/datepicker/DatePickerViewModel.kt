package com.example.calorie_vault.ui.datepicker

import androidx.lifecycle.ViewModel
import com.example.calorie_vault.data.mealdata.MealDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DatePickerViewModel @Inject constructor(
    private val mealDao: MealDao
) : ViewModel(){

    // Use MealDAO directly in the Dialog's VM? I don't think so.

}