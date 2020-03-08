package net.sokum.modern.app.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import net.sokum.modern.app.api.GitHubApiService
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNewsApiService(okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory?)
            = provideService(okHttpClient, converterFactory, GitHubApiService::class.java)

    private fun <T> provideService(okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory?, clazz: Class<T>): T {
        return createRetrofit(okHttpClient, converterFactory).create(clazz)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Singleton
    @Provides
    fun createRetrofit(okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory?) : Retrofit {
        var builder = Retrofit.Builder()
            builder.baseUrl(GitHubApiService.ENDPOINT)
                .client(okHttpClient)


            converterFactory?.let {
                builder.addConverterFactory(it)
            }

        return builder.build()
    }
}