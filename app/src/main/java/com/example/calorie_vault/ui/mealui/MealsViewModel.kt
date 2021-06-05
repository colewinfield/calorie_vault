package com.example.calorie_vault.ui.mealui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.calorie_vault.data.mealdata.Meal
import com.example.calorie_vault.data.mealdata.MealDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealDao : MealDao
) : ViewModel() {


    // whenever the table updates, new list of meals through this flow.
    // viewModel should never have a reference to the fragment to avoid memory leaks
    val meals = mealDao.getMeals().asLiveData()

    private val eventsChannel = Channel<MealsEvent>()
    val mealsEvents = eventsChannel.receiveAsFlow()


    fun onAddMealClicked() = viewModelScope.launch {
        eventsChannel.send(MealsEvent.NavigateToAddScreen)
    }

    fun onEditClicked(meal: Meal) = viewModelScope.launch {
        eventsChannel.send(MealsEvent.NavigateToEditScreen(meal))
    }


    /**
     * These are the events that'll be sent back to the fragment to determine what's happening.
     * Used in navigating to the edit or add screen (same fragment). Also used for other one
     * time events. This is in lieu of using a SingleEvent wrapper with LiveData.
     */
    sealed class MealsEvent {
        object NavigateToAddScreen : MealsEvent()
        data class NavigateToEditScreen(val meal: Meal) : MealsEvent()
    }
}

