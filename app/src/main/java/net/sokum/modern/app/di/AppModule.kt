package net.sokum.modern.app.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import net.sokum.modern.app.App
import net.sokum.modern.app.data.LocalUsersRepository
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application : App) : Context {
        return application
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()


    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson? = null): GsonConverterFactory {
        if (gson == null) {
            return GsonConverterFactory.create(provideGson())
        }
        return GsonConverterFactory.create(gson)
    }
}