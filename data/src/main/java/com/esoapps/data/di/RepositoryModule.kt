package com.esoapps.data.di

import com.esoapps.data.repository.MainRepositoryImpl
import com.esoapps.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun binLMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}