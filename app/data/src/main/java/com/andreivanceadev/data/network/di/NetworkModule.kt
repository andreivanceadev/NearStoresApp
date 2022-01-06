package com.andreivanceadev.data.network.di

import android.content.Context
import com.andreivanceadev.data.network.NearStoresRetrofit
import com.andreivanceadev.data.network.stores.StoresAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = NearStoresRetrofit.getRetrofit()

    @Provides
    @Singleton
    fun provideStoresAPI(retrofit: Retrofit): StoresAPI = retrofit.create(StoresAPI::class.java)

    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context) = context

}