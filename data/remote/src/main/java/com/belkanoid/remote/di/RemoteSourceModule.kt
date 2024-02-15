package com.belkanoid.remote.di

import com.belkanoid.remote.EffectiveMobileShopApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RemoteSourceModule {

    @Provides
    fun provideEffectiveMobileShopApi(): EffectiveMobileShopApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EffectiveMobileShopApi::class.java)


    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }

}