package com.example.mytask5.di

import android.content.Context
import com.bangkit.freshcanapp.data.remote.retrofit.ApiConfig
import com.dicoding.myunlimitedquotes.data.NewsRepository

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        return NewsRepository(apiService)
    }
}