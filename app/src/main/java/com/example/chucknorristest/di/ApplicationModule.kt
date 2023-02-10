package com.example.chucknorristest.di

import android.app.Application
import android.content.Context
import com.example.chucknorristest.rest.ChuckNorrisRepository
import com.example.chucknorristest.utils.ChuckNorrisViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context =
        application.applicationContext

    @Provides
    fun providesChuckNorrisViewModelFactory(
        repository: ChuckNorrisRepository,
        ioDispatcher: CoroutineDispatcher
    ): ChuckNorrisViewModelFactory =
        ChuckNorrisViewModelFactory(repository, ioDispatcher)
}