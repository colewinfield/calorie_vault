package com.example.calorie_vault.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.calorie_vault.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao() : MealDao

    class Callback @Inject constructor(
        private val database: Provider<MealDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        // database onCreate is only called once -- for obvious reasons. Need to reinstall if you
        // want to update the default list.
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().mealDao()
            // db operations
            applicationScope.launch {
                dao.insert(Meal("Bagels and cream cheese",411, description = "Deliciously yummy!", worthIt = true))
                dao.insert(Meal("PB & J",695))
                dao.insert(Meal("Ice cream (neapolitan)",1111, description = "Wasn't that great, sadly.", worthIt = false))
                dao.insert(Meal("Coco Puffs",314, description = "I ran out of milk too quickly."))
                dao.insert(Meal("Bagels and cream cheese",411, description = "My second bagel of the day!", worthIt = false))

            }

        }
    }

}