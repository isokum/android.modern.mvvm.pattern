package net.sokum.modern.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.sokum.modern.app.ui.main.MainActivity
import net.sokum.modern.app.ui.main.MainModule
import net.sokum.modern.app.ui.main.local.LocalUsersFragment
import net.sokum.modern.app.ui.main.local.LocalUsersModule
import net.sokum.modern.app.ui.main.remote.RemoteUsersFragment
import net.sokum.modern.app.ui.main.remote.RemoteUsersModule

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector(modules = [RemoteUsersModule::class])
    abstract fun contributeRemoteUsersFragment() : RemoteUsersFragment

    @ContributesAndroidInjector(modules = [LocalUsersModule::class])
    abstract fun contributeLocalUsersFragment() : LocalUsersFragment
}