package com.example.knownyc.util

import android.util.Log
import com.example.knownyc.commons.TAG
import com.example.knownyc.data.local.provider.AssetsProvider
import com.example.knownyc.data.local.provider.AssetsProviderImpl
import com.example.knownyc.data.local.repositories.BoroughsRepositoryImpl
import com.example.knownyc.data.remote.repositories.NycOpenDataApiService
import com.example.knownyc.data.remote.repositories.NycParksRepositoryImpl
import com.example.knownyc.domain.repositories.BoroughsRepository
import com.example.knownyc.domain.repositories.NycParksRepository
import com. jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt. InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
abstract class AppProviderModule {


    // Local Assets
    @Binds
    @Singleton
    abstract fun localAssetsProvider(impl: AssetsProviderImpl): AssetsProvider

    // Repositories
    @Binds
    @Singleton
    abstract fun boroughsRepositoryProvider(impl: BoroughsRepositoryImpl): BoroughsRepository


    @Binds
    @Singleton
    abstract fun nycParksRepositoryProvider(impl: NycParksRepositoryImpl): NycParksRepository

    // API service
    companion object {

        @Singleton
        @Provides
        fun nycOpenDataApiServiceProvider(): NycOpenDataApiService {
            val json = Json {
                ignoreUnknownKeys = true
            }

            Log.d(TAG, "building NYC Open Data API service provider")

            return Retrofit.Builder().addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            ).baseUrl(AppConstants.NYC_OPEN_DATA_API_BASE_URL).build()
                .create(NycOpenDataApiService::class.java)
        }
    }
}