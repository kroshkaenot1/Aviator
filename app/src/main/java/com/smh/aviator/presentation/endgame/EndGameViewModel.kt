package com.smh.aviator.presentation.endgame

import androidx.lifecycle.ViewModel
import com.smh.aviator.domain.usecase.GetBestScoreUseCase
import com.smh.aviator.domain.usecase.SaveScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EndGameViewModel @Inject constructor(
    private val saveScoreUseCase: SaveScoreUseCase,
    private val getBestScoreUseCase: GetBestScoreUseCase
) : ViewModel() {

    fun saveScoreToSharedPreferences(score: Int) : Boolean =
        saveScoreUseCase.execute(score = score)

    fun getScoreFromSharedPreferences(): Int =
        getBestScoreUseCase.execute()
}