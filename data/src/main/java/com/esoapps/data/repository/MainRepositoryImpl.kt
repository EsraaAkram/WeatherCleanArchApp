package com.esoapps.data.repository

import com.esoapps.data.network.ApiService
import com.esoapps.domain.repository.MainRepository
import com.esoapps.data.utils.Constants
import com.esoapps.domain.entity.ApiData
import com.esoapps.domain.entity.ApiModel
import com.esoapps.domain.ext.DispatcherProvider
import com.esoapps.domain.ext.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatcherProvider: DispatcherProvider
) : MainRepository {

    override suspend fun getApiData(location: String): Flow<Result<ApiData>> {
        return withContext(dispatcherProvider.io) {
            flow {
                emit(Result.Loading)
                val response: Response<ApiModel> = apiService.getApiData(location = location)
                if (response.isSuccessful) {
                    response.body()?.let {

                        emit(Result.Success(data = it.apiData!!))
                    } ?: emit(Result.Error(message = Constants.ERROR_MESSAGE))
                } else {
                    emit(
                        Result.Error(
                            message = Constants.ERROR_MESSAGE,

                            )
                    )
                }

            }.catch { error ->
                emit(Result.Error(message = error.message ?: Constants.ERROR_MESSAGE))
            }
        }
    }
}