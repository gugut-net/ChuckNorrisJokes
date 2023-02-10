package com.example.chucknorristest.di

import com.example.chucknorristest.MainActivity
import com.example.chucknorristest.utils.ui.BaseFragment
import dagger.Component

@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    ApplicationModule::class
])
interface ChuckNorrisComponent {

    /**
     * Injects dependencies injection in MainActivity
     */
    fun inject(mainActivity: MainActivity)

    /**
     * Injects dependencies injection in Base Fragment
     */
    fun inject(baseFragment: BaseFragment)
}