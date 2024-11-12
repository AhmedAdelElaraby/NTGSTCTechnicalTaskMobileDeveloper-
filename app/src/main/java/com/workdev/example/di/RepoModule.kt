package com.workdev.example.di

import com.workdev.data.remote.ApiService
import com.workdev.data.repo.getAllRepoImpL
import com.workdev.domain.repo.getAllRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiService) :getAllRepo{

        return getAllRepoImpL(apiService)
    }
}