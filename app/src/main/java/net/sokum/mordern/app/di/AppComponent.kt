package net.sokum.mordern.app.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import net.sokum.mordern.app.App
import net.sokum.mordern.app.ui.NewsListFragmentProvider
import net.sokum.mordern.app.ui.NewsListModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    ActivityBuilder::class
])
abstract class AppComponent : AndroidInjector<App> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}