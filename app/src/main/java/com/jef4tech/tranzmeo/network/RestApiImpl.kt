package com.jef4tech.tranzmeo.network

/**
 * @author jeffin
 * @date 30/01/23
 */
object RestApiImpl {
    suspend fun getUserList(limit: Int, skip: Int) = RetrofitClientFactory.restApis.getAllUser(limit,skip)
    suspend fun getUserData(userId: Int) = RetrofitClientFactory.restApis.getUserData(userId)
}