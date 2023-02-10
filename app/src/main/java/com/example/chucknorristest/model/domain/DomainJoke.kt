package com.example.chucknorristest.model.domain

import com.example.chucknorristest.model.ChuckNorrisResponse

data class DomainJokes(
    val jokesValues: String
)


fun ChuckNorrisResponse.mapToDomainJoke(): DomainJokes {
    return DomainJokes(
        jokesValues = this.value ?: "Joke not found"
    )
}