package com.example.android.guesstheword.screens.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel() {

    // The current word
    var word = MutableLiveData<String>()

    // The current score
    var score = MutableLiveData<Int>()

    private lateinit var wordList: MutableList<String>
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }
    init {
        word.value =""
        score.value = 0
        resetList()
        nextWord()
        Timber.i("GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("GameViewModel destroyed!")
    }
    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            word.value = wordList.removeAt(0)
        }
    }


}