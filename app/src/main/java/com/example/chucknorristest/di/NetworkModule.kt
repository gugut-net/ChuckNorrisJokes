package com.example.chucknorristest.di

import com.example.chucknorristest.rest.ChuckNorrisAPI
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Module
class NetworkModule {

    @Provides
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ChuckNorrisAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

//    @Provides
//    @Named("other")
//    fun providesRetrofit2(
//        okHttpClient: OkHttpClient
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(StarWarsApi.BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//    }

    @Provides
    fun providesOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * IO ....
     */
    @Provides
    fun providesChuckNorrisService(retrofit: Retrofit): ChuckNorrisAPI {
        return retrofit.create(ChuckNorrisAPI::class.java)
    }

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher =
        Dispatchers.IO
}