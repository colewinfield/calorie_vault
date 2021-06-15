package com.example.calorie_vault.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.calorie_vault.data.mealdata.Meal
import com.example.calorie_vault.data.mealdata.MealDao
import com.example.calorie_vault.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Meal::class], version = 1, exportSchema = false)
@TypeConverters(AppDatabase.Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealDao() : MealDao

    class Callback @Inject constructor(
        private val database: Provider<AppDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        // database onCreate is only called once -- for obvious reasons. Need to reinstall if you
        // want to update the default list.
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().mealDao()
            // db operations
            applicationScope.launch {
                dao.insert(Meal("Bagels and cream cheese",411))
                dao.insert(Meal("PB & J",695))
                dao.insert(Meal("Ice cream (neapolitan)",1111))
                dao.insert(Meal("Coco Puffs",314))
                dao.insert(Meal("Bagels and cream cheese",411))

            }

        }
    }

    class Converters {
        @TypeConverter
        fun fromTimeStamp(value: Long?) : Date? {
            return value?.let { Date(it) }
        }

        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time?.toLong()
        }
    }

}