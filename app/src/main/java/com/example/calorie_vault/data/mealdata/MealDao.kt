package com.example.calorie_vault.data.mealdata

import android.content.ContentValues.TAG
import android.util.Log
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

@Dao
interface MealDao {

    fun getMeals(date: Date) : Flow<List<Meal>> {
        Log.d(TAG, "getMeals: Called after DatePicked")
        val dateToTimeConverter = DateToTimeConverter
        val startOfDay = dateToTimeConverter.atStartOfDate(date)
        val endOfDay = dateToTimeConverter.atEndOfDay(date)
        return getMealsByDate(startOfDay, endOfDay)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal: Meal)

    @Update
    suspend fun update(meal: Meal)

    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM meal_table WHERE date BETWEEN :startOfDay AND :endOfDay")
    fun getMealsByDate(startOfDay: Date, endOfDay: Date): Flow<List<Meal>>

}

object DateToTimeConverter {
    fun atStartOfDate(date: Date) : Date {
        val localDateTime = dateToLocalDateTime(date)
        val startOfDay: LocalDateTime = localDateTime.with(LocalTime.MIN)
        return localDateTimeToDate(startOfDay)
    }

    fun atEndOfDay(date: Date) : Date {
        val localDateTime = dateToLocalDateTime(date)
        val endOfDay: LocalDateTime = localDateTime.with(LocalTime.MAX)
        return localDateTimeToDate(endOfDay)
    }

    private fun dateToLocalDateTime(date: Date) : LocalDateTime {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
    }

    private fun localDateTimeToDate(localDateTime: LocalDateTime) : Date {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
    }
}