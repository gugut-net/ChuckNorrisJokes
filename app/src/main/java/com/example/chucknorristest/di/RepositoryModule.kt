package com.example.chucknorristest.di

import com.example.chucknorristest.rest.ChuckNorrisRepository
import com.example.chucknorristest.rest.ChuckNorrisRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesChuckNorrisRepository(
        chuckNorrisImpl: ChuckNorrisRepositoryImpl
    ): ChuckNorrisRepository
}