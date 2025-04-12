package com.proNutriLog.proteinCalculator.data.remote
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object KrogerApiModule {

    @dagger.Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.kroger.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @dagger.Provides
    @Singleton
    fun provideKrogerApiService(retrofit: Retrofit): KrogerApiService {
        return retrofit.create(KrogerApiService::class.java)
    }
}
