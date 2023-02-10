package com.example.chucknorristest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorristest.model.ChuckNorrisResponse
import com.example.chucknorristest.rest.ChuckNorrisRepository
import com.example.chucknorristest.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "ChuckNorrisViewModel"

class ChuckNorrisViewModel(
    private val repository: ChuckNorrisRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    init {
        getData()
    }

    private val _randomJoke: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val randomJoke: LiveData<UIState> get() = _randomJoke

    fun getData() {
        //If the Dispatcher is not provider, it's going to work on the main thread.
        viewModelScope.launch(ioDispatcher) {
            repository.getRandomJoke().collect {
                _randomJoke.postValue(it)
            }
        }
    }
}