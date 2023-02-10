package com.example.chucknorristest.usecase.chucknorris

import com.example.chucknorristest.rest.ChuckNorrisRepository
import javax.inject.Inject

class ReviewUseCase @Inject constructor(
    private val repository: ChuckNorrisRepository
) {
    
}