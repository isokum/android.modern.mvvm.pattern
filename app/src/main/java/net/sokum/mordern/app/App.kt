package net.sokum.mordern.app

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import net.sokum.mordern.app.di.DaggerAppComponent
import javax.inject.Inject


class App : Application() {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        DaggerAppComponent.factory().create(this)

        super.onCreate()
    }
}