package net.sokum.mordern.app.di

import android.content.Context
import dagger.Module
import net.sokum.mordern.app.App
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    fun provideContext(application : App) : Context {
        return application
    }
}