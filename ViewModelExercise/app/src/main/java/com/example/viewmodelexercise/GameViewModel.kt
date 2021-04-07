package com.example.viewmodelexercise

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var score = 0;
    fun scorePlus() {
        score++;
    }
}