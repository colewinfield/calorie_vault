package com.example.calorie_vault.di

import android.app.Application
import androidx.room.Room
import com.example.calorie_vault.data.mealdata.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        app: Application,
        callback: MealDatabase.Callback
    ) = Room.databaseBuilder(app, MealDatabase::class.java, "meal_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun providesMealDao(db: MealDatabase) = db.mealDao()

    @ApplicationScope
    @Singleton
    @Provides
    fun providesApplicationScope() = CoroutineScope(SupervisorJob())

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope