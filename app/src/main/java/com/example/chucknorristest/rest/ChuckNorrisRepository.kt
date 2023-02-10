package com.example.chucknorristest.rest

import android.content.ContentValues.TAG
import android.util.Log
import com.example.chucknorristest.model.ChuckNorrisResponse
import com.example.chucknorristest.model.domain.mapToDomainJoke
import com.example.chucknorristest.utils.ErrorResponse
import com.example.chucknorristest.utils.NullChuckNorrisResponse
import com.example.chucknorristest.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ChuckNorrisRepository {

    /**
     *
     */
    fun getRandomJoke(): Flow<UIState>
}

class ChuckNorrisRepositoryImpl @Inject constructor(
    private val chuckNorrisApi: ChuckNorrisAPI
) : ChuckNorrisRepository{

    override fun getRandomJoke(): Flow<UIState> = flow {
        emit(UIState.LOADING)
        try{
            val response = chuckNorrisApi.getJoke()
            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.SUCCESS(it.mapToDomainJoke()))
                }?: throw Exception("Null joke")
            }else{
                throw Exception(response.errorBody()?.string())
            }
        }catch (e: Exception){
            emit(UIState.ERROR(e))
        }
    }

}