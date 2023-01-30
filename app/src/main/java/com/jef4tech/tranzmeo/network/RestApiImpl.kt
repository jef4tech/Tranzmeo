package com.jef4tech.tranzmeo.network

/**
 * @author jeffin
 * @date 30/01/23
 */
object RestApiImpl {
    suspend fun getUserList(searchWord: String, page: Int) = RetrofitClientFactory.restApis.getAllUser(10,20)
    suspend fun getUserData(userName: String) = RetrofitClientFactory.restApis.getUserData(userName)
}