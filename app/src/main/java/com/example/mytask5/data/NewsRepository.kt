package com.dicoding.myunlimitedquotes.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.bangkit.freshcanapp.data.remote.retrofit.ApiService
import com.example.mytask5.data.response.ArticlesItem
import com.example.mytask5.data.response.Response
import retrofit2.Call

class NewsRepository(private val apiService: ApiService) {
    fun getAllNews(): LiveData<PagingData<ArticlesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                NewsPagingSource(apiService)
            }
        ).liveData
    }

    fun getHeadlineNews(): Call<Response> {
        return apiService.getHeadlineNews()
    }
}