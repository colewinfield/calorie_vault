package com.example.calorie_vault.data.userdata

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    val userName: String,
    val bodyWeight: Double,
    val goalWeight: Double,
    val gender: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
) : Parcelable {

}