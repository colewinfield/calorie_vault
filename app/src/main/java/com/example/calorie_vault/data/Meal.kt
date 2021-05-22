package com.example.calorie_vault.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "meal_table")
data class Meal(
    val meal: String,
    val calories: Int,
    val description: String,
    val date: Date,
    val worthIt: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable { }

