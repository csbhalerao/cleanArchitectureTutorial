package com.chetan.coroutinetutorial.di

import android.content.Context
import com.chetan.coroutinetutorial.BuildConfig
import com.chetan.coroutinetutorial.util.hasNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    companion object {
        const val BASE_URL = "https://www.googleapis.com/"
        const val READ_TIMEOUT = 10L
        const val CONNECT_TIMEOUT = 10L
    }

    @Singleton
    @Provides
    fun provideHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        // caching in bytes
        val cacheSize = (5 * 1024 * 1024).toLong()
        val appCache = Cache(appContext.cacheDir, cacheSize)
        return OkHttpClient
            .Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .cache(appCache)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor {
                var request = it.request()
                request = if (hasNetwork(appContext)) {
                    // discard cache after 10 sec if network is present for same network request
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 10).build()
                } else {
                    // if no network show old data which is not old than 7 days
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                }
                it.proceed(request)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}
