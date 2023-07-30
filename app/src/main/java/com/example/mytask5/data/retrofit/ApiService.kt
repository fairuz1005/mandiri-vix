package com.bangkit.freshcanapp.data.remote.retrofit

import com.example.mytask5.API_KEY
import com.example.mytask5.COUNTRY
import com.example.mytask5.HEADLINES_PAGE_SIZE
import com.example.mytask5.Q
import com.example.mytask5.data.response.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("v2/everything/")
    suspend fun getAllNews(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("q") q: String = Q,
        @Query("apiKey") apiKey: String = API_KEY,
    ): Response

    @GET("v2/top-headlines/")
    fun getHeadlineNews(
        @Query("pageSize") pageSize: Int = HEADLINES_PAGE_SIZE,
        @Query("country") country: String = COUNTRY,
        @Query("apiKey") apiKey: String = API_KEY,
    ): Call<Response>
}