package com.example.calorie_vault.ui.addeditmealsui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorie_vault.data.mealdata.Meal
import com.example.calorie_vault.data.mealdata.MealDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditMealsViewModel @Inject constructor(
    private val mealDao: MealDao,
    private val state: SavedStateHandle
): ViewModel() {

    private val TAG = "AddEditMealsViewModel"

    private val eventsChannel = Channel<AddEditMealsEvent>()
    val addEditChannel = eventsChannel.receiveAsFlow()

    val meal = state.get<Meal>("meal")

    var mealName = state.get<String>("mealName") ?: meal?.meal ?: ""
        set(value) {
            field = value
            state.set("mealName", value)
        }


    var calories = state.get<Int>("calories") ?: meal?.calories ?: 0
        set(value) {
            field = value
            state.set("calories", value)
        }

    fun onSaveClick() {
        if (mealName.isBlank()) {
            sendErrorMessage()
            return
        }

        if (meal != null) {
            val updatedMeal = meal.copy(meal = mealName, calories = calories)
            updateMeal(updatedMeal)
        } else {
            val newMeal = Meal(meal = mealName, calories = calories)
            createMeal(newMeal)
        }
    }

    private fun sendErrorMessage() = viewModelScope.launch {
        eventsChannel.send(AddEditMealsEvent.ShowErrorMessage("Name cannot be blank"))
    }

    private fun createMeal(newMeal: Meal) = viewModelScope.launch{
        mealDao.insert(newMeal)
        // TODO: Redo this -- use resultsFragment
        eventsChannel.send(AddEditMealsEvent.ShowSuccessMessage(newMeal))
        // send event back to Fragment to show SnackBar and send back meal for undoing
    }

    private fun updateMeal(updatedMeal: Meal) = viewModelScope.launch {
        mealDao.update(updatedMeal)
        eventsChannel.send(AddEditMealsEvent.ShowSuccessMessage(updatedMeal))
        // send event back to Fragment to show SnackBar and send back meal for undoing
    }

    sealed class AddEditMealsEvent {
        data class ShowSuccessMessage(val newMeal: Meal) : AddEditMealsEvent()
        data class ShowErrorMessage(val error: String) : AddEditMealsEvent()
    }

}