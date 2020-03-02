package net.sokum.mordern.app.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import net.sokum.mordern.app.api.AuthInterceptor
import net.sokum.mordern.app.api.NewsApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import net.sokum.mordern.app.BuildConfig
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNewsApiService(okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory?)
            = provideService(okHttpClient, converterFactory, NewsApiService::class.java)

    private fun <T> provideService(okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory?, clazz: Class<T>): T {
        return createRetrofit(okHttpClient, converterFactory).create(clazz)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(AuthInterceptor(BuildConfig.API_DEVELOPER_TOKEN)).build()
    }

    @Singleton
    @Provides
    fun createRetrofit(okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory?) : Retrofit {
        var builder = Retrofit.Builder()
            builder.baseUrl(NewsApiService.ENDPOINT)
                .client(okHttpClient)


            converterFactory?.let {
                builder.addConverterFactory(it)
            }

        return builder.build()
    }
}