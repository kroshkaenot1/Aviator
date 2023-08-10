package com.smh.aviator.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val AVIATOR_PREFERENCES = "AVIATOR_PREFS"

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) : SharedPreferences =
        context.getSharedPreferences(AVIATOR_PREFERENCES,Context.MODE_PRIVATE)
}