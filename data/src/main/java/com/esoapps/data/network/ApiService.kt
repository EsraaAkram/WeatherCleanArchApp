package com.esoapps.data.network

import com.esoapps.data.BuildConfig
import com.esoapps.domain.entity.ApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current.json")
    suspend fun getApiData(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") location: String ,
        @Query("days") days: Int = 1,
    ): Response<ApiModel>


}