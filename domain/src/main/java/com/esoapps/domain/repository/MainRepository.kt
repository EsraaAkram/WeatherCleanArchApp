package com.esoapps.domain.repository

import com.esoapps.domain.entity.ApiData

import com.esoapps.domain.ext.Result
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getApiData(location: String): Flow<Result<ApiData>>

}