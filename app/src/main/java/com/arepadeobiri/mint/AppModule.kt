package com.arepadeobiri.mint

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingLevel = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply { httpLoggingInterceptor.level = loggingLevel }
    }


    @Provides
    @Singleton
    fun provideBinList(
        client: OkHttpClient
    ): BinList =
        Retrofit.Builder().apply {
            baseUrl("https://lookup.binlist.net/")
            client(client)
            addConverterFactory(GsonConverterFactory.create())
        }.build().create(BinList::class.java)



    @Provides
    fun getClient(
        httpLoggingInterceptor : HttpLoggingInterceptor
    ):OkHttpClient=  OkHttpClient.Builder()
        .addNetworkInterceptor(httpLoggingInterceptor)
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(90, TimeUnit.SECONDS)
        .build()



}