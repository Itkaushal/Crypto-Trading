package com.app.kaushalprajapati.myapplication.tabscreen

import androidx.paging.PagingSource
import androidx.paging.PagingState

class TabPagingSource: PagingSource<Int, String>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        val page = params.key ?: 1
        val data = (1..20).map { "Item $it from Page $page" } // Simulated data
        return LoadResult.Page(
            data = data,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == 3) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? = null
}