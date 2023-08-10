package com.smh.aviator.di

import com.smh.aviator.domain.usecase.GetBestScoreUseCase
import com.smh.aviator.domain.usecase.SaveScoreUseCase
import com.smh.aviator.domain.repository.ScoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Singleton
    @Provides
    fun provideScoreRepositoryToGetBestScoreUseCase(Impl: ScoreRepository) =
        GetBestScoreUseCase(scoreRepository = Impl)
    @Singleton
    @Provides
    fun provideScoreRepositoryToSaveScoreUseCase(Impl: ScoreRepository) =
        SaveScoreUseCase(scoreRepository = Impl)
}