package com.smh.aviator.domain.usecase

import com.smh.aviator.domain.repository.ScoreRepository
import javax.inject.Inject

class SaveScoreUseCase @Inject constructor(private val scoreRepository: ScoreRepository) {
    fun execute(score: Int) : Boolean =
        scoreRepository.saveBestScore(score = score)
}