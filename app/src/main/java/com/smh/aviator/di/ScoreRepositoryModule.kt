package com.smh.aviator.di

import com.smh.aviator.data.repositoryimpl.ScoreRepositoryImpl
import com.smh.aviator.domain.repository.ScoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ScoreRepositoryModule {
    @Binds
    abstract fun bindScoreRepositoryImplToScoreRepository(Impl: ScoreRepositoryImpl): ScoreRepository
}