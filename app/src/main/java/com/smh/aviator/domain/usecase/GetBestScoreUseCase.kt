package com.smh.aviator.domain.usecase

import com.smh.aviator.domain.repository.ScoreRepository
import javax.inject.Inject

class GetBestScoreUseCase @Inject constructor(private val scoreRepository: ScoreRepository) {
    fun execute(): Int =
        scoreRepository.getBestScore()
}