package com.dicoding.myunlimitedquotes.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bangkit.freshcanapp.data.remote.retrofit.ApiService
import com.example.mytask5.data.response.ArticlesItem
import com.example.mytask5.data.response.Response

class NewsPagingSource(private val apiService: ApiService) : PagingSource<Int, ArticlesItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getAllNews(page, params.loadSize).articles

            LoadResult.Page(
                data = responseData!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}