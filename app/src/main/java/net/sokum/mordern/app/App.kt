package net.sokum.mordern.app

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import net.sokum.mordern.app.di.DaggerAppComponent
import javax.inject.Inject


class App : Application(), HasAndroidInjector{
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.factory().create(this).inject(this)
    }

    override fun androidInjector() = androidInjector
}