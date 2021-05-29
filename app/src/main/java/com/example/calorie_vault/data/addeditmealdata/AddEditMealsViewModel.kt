package com.example.calorie_vault.data.addeditmealdata

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.calorie_vault.data.mealdata.Meal
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

    val meal = state.get<Meal>("meal")

    var mealName = state.get<String>("mealName") ?: meal?.meal ?: ""
        set(value) {
            field = value
            state.set("mealName", value)
        }

    var mealDescription = state.get<String>("mealDescription") ?: meal?.description ?: ""
        set(value) {
            field = value
            state.set("mealDescription", value)
        }

    var mealWorthIt = state.get<Boolean>("mealWorthIt") ?: meal?.worthIt ?: false
        set(value) {
            field = value
            state.set("mealWorthIt", value)
        }

    var calories = state.get<Int>("calories") ?: meal?.calories ?: 0
        set(value) {
            field = value
            state.set("calories", value)
        }

}