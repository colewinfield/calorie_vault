package com.example.calorie_vault.data.mealdata

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.util.*

@Parcelize
@Entity(tableName = "meal_table")
data class Meal(
    val meal: String,
    val calories: Int,
    val date: Date = Date(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {

//    val dateCreated: String
//        get() = DateFormat.getDateInstance().format(date)

}

