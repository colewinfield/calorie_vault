package com.example.calorie_vault.ui.mealui

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.calorie_vault.data.mealdata.Meal
import com.example.calorie_vault.data.mealdata.MealDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealDao : MealDao
) : ViewModel() {

    private val TAG = "MealsViewModel"

    private val currentDay: MutableLiveData<Date> = MutableLiveData(Date())
    val meals = Transformations.switchMap(currentDay){ date -> mealDao.getMeals(date).asLiveData() }

    private val eventsChannel = Channel<MealsEvent>()
    val mealsEvents = eventsChannel.receiveAsFlow()

    val isEmptyMeals = meals.map {
        if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
    }


    fun onAddMealClicked() = viewModelScope.launch {
        eventsChannel.send(MealsEvent.NavigateToAddScreen)
    }

    fun onEditClicked(meal: Meal) = viewModelScope.launch {
        eventsChannel.send(MealsEvent.NavigateToEditScreen(meal))
    }

    fun onNewDateClicked() = viewModelScope.launch {
        eventsChannel.send(MealsEvent.NavigateToNewDateScreen)
    }


    fun setDate(year: Int?, month: Int?, day: Int?) {
        if (year != null && month != null && day != null) {
            val date = GregorianCalendar(year, month, day).time
            currentDay.value = date
        }

    }


    /**
     * These are the events that'll be sent back to the fragment to determine what's happening.
     * Used in navigating to the edit or add screen (same fragment). Also used for other one
     * time events. This is in lieu of using a SingleEvent wrapper with LiveData.
     */
    sealed class MealsEvent {
        object NavigateToAddScreen : MealsEvent()
        object NavigateToNewDateScreen : MealsEvent()
        data class NavigateToEditScreen(val meal: Meal) : MealsEvent()
    }
}

