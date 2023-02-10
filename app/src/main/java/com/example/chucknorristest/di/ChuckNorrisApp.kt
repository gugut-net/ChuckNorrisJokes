package com.example.chucknorristest.di

import android.app.Application

class ChuckNorrisApp : Application() {

    override fun onCreate() {
        super.onCreate()

        chuckNorrisComponent = DaggerChuckNorrisComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var chuckNorrisComponent: ChuckNorrisComponent
    }
}