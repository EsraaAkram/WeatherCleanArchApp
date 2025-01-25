package com.esoapps.domain.usecase

import com.esoapps.domain.mapper.MainMapper
import com.esoapps.domain.model.MainModel

import com.esoapps.domain.repository.MainRepository
import com.esoapps.domain.ext.Result
import com.esoapps.domain.ext.mapFrom
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val mainMapper: MainMapper
) {
    suspend operator fun invoke(location: String): Flow<Result<MainModel>> {
        return mapFrom(mainRepository.getApiData(location = location), mainMapper)
    }
}