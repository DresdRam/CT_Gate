package com.mayv.ctgate.di

import android.content.Context
import com.mayv.ctgate.network.SMISApi
import com.mayv.ctgate.repository.LoginRepository
import com.mayv.ctgate.repository.LogsRepository
import com.mayv.ctgate.repository.SearchRepository
import com.mayv.ctgate.repository.SoldierRepository
import com.mayv.ctgate.utils.Constants
import com.mayv.ctgate.utils.PreferenceHelper
import com.mayv.ctgate.utils.PreferenceHelper.ip
import com.mayv.ctgate.utils.PreferenceHelper.port
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSoldierRepository(api: SMISApi) = SoldierRepository(api)

    @Singleton
    @Provides
    fun provideLogsRepository(api: SMISApi) = LogsRepository(api)

    @Singleton
    @Provides
    fun provideLoginRepository(api: SMISApi) = LoginRepository(api)

    @Singleton
    @Provides
    fun provideSearchRepository(api: SMISApi) = SearchRepository(api)

    @Singleton
    @Provides
    fun provideSMISApi(@ApplicationContext appContext: Context): SMISApi {
        val preferences = PreferenceHelper.getPreference(appContext)
        val BASE_URL = "http://${preferences.ip}:${preferences.port}/"
        
        /*val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.MINUTES).build()*/

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SMISApi::class.java)
    }

}