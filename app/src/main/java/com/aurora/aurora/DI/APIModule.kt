package com.aurora.aurora.DI

import android.content.Context
import com.aurora.aurora.API_Services.UserAPI_Service
import com.aurora.aurora.API_Services.YoutubeAPI_Service
import com.aurora.aurora.AppConfig.BaseAPI.BaseAPI
import com.google.firebase.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {


    @Provides
    @Singleton
    @Named("youtube")
    fun provideYoutubeBaseUrl() = BaseAPI.BASE_YOUTUBE_URL

    @Provides
    @Singleton
    @Named("user")
    fun provideUserBaseUrl() = BaseAPI.BASE_API

    @Provides
    @Singleton
    fun provideConnectionTimeout() = BaseAPI.NETWORK_TIMEOUT

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()




    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }
        OkHttpClient
            .Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }


    @Provides
    @Singleton
    fun provideYoutubeAPI(@Named("youtube") baseUrl: String, gson: Gson, client: OkHttpClient): YoutubeAPI_Service =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(YoutubeAPI_Service::class.java)



    @Provides
    @Singleton
    fun provideUserAPI(@Named("user") baseUrl: String, gson: Gson, client: OkHttpClient): UserAPI_Service =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserAPI_Service::class.java)


}