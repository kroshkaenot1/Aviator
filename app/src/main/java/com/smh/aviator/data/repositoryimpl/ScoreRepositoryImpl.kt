package com.smh.aviator.data.repositoryimpl

import android.content.SharedPreferences
import com.smh.aviator.domain.repository.ScoreRepository
import javax.inject.Inject

const val SCORE = "score"

class ScoreRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ScoreRepository {
    override fun getBestScore(): Int {
        return sharedPreferences.getInt(SCORE, 0)
    }

    override fun saveBestScore(score: Int) : Boolean{
        val bestScore = getBestScore()
        if (score > bestScore) {
            sharedPreferences.edit().putInt(SCORE, score).apply()
            return true
        }
        return false
    }
}