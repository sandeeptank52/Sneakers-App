package com.sneakersapp.di

import com.google.gson.GsonBuilder
import com.sneakersapp.data.remote.AppApis
import com.sneakersapp.data.remote.repo.ProductsRepositoryImpl
import com.sneakersapp.domain.repo.ProductsRepository
import com.sneakersapp.utils.APIConstants
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
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): AppApis {
        return retrofit.create(AppApis::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRepository(apis: AppApis): ProductsRepository {
        return ProductsRepositoryImpl(appApi = apis)
    }
}