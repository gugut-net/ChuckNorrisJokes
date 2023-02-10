package com.example.chucknorristest.utils

class NullChuckNorrisResponse(message: String = "Norris response is null") : Exception(message)

class ErrorResponse(message: String?) : Exception(message)