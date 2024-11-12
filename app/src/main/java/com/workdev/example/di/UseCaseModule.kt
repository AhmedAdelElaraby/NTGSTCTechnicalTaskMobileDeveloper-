package com.workdev.example.di

import dagger.Module
import com.workdev.domain.repo.getAllRepo
import com.workdev.domain.usecase.getAllUseCase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun ProvideUseCase(Repo: getAllRepo) :getAllUseCase{

        return getAllUseCase(Repo)

    }


}