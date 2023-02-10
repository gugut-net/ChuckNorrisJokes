package com.example.chucknorristest.utils

import com.example.chucknorristest.model.domain.DomainJokes

sealed class UIState {
    /**
     * It's necessary because you will show the steps we are doing in the UI.
     */
    object LOADING : UIState()
    data class SUCCESS(val response: DomainJokes) : UIState()
    class ERROR(val e: Exception) : UIState()
}