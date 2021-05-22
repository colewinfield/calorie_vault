package com.example.calorie_vault.di

import android.app.Application
import androidx.room.Room
import com.example.calorie_vault.data.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(app, MealDatabase::class.java, "meal_database")
        .fallbackToDestructiveMigration()
        .addCallback()
        .build()


    @Provides
    fun providesMealDao(db: MealDatabase) = db.mealDao()

}