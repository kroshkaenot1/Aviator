package com.smh.aviator.domain.repository

interface ScoreRepository {
    fun getBestScore(): Int
    fun saveBestScore(score: Int) : Boolean
}