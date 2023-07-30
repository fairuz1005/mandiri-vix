package com.example.mytask5

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.myunlimitedquotes.data.NewsRepository
import com.example.mytask5.data.response.ArticlesItem
import com.example.mytask5.data.response.Response
import com.example.mytask5.di.Injection
import retrofit2.Call
import retrofit2.Callback

class MainViewModel(private val repo: NewsRepository): ViewModel() {
    val allNews: LiveData<PagingData<ArticlesItem>> =
        repo.getAllNews().cachedIn(viewModelScope)

    private val _headlineNews = MutableLiveData<ArticlesItem>()
    val headlineNews: LiveData<ArticlesItem> = _headlineNews

    fun getHeadlineNews() {
        repo.getHeadlineNews().enqueue(object: Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if(response.isSuccessful){
                    _headlineNews.value = response.body()!!.articles?.get(0)
                }else{
                }

            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
            }

        })
    }
}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}