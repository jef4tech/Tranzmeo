package com.jef4tech.tranzmeo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jef4tech.tranzmeo.models.UsersResponse
import com.jef4tech.tranzmeo.network.RetrofitClientFactory

/**
 * @author jeffin
 * @date 31/01/23
 */
class PostDataSource(private val retrofitClientFactory: RetrofitClientFactory):
    PagingSource<Int, UsersResponse.User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UsersResponse.User> {
        try {
            val currentLoadingPageKey = params.key ?: 10
            val response = retrofitClientFactory.restApis.getAllUser(10,currentLoadingPageKey)
            val responseData = mutableListOf<UsersResponse.User>()
            val data = response.body()?.users ?: emptyList()
            responseData.addAll(data)

//            val prevKey = null

            return LoadResult.Page(
                data = responseData,
                prevKey = null,
                nextKey = currentLoadingPageKey.plus(10)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UsersResponse.User>): Int? {
        return null
    }


}