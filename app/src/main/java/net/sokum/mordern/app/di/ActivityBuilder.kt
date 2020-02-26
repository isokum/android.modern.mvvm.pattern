package net.sokum.mordern.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.sokum.mordern.app.MainActivity
import net.sokum.mordern.app.MainActivityModule
import net.sokum.mordern.app.ui.NewsListFragment
import net.sokum.mordern.app.ui.NewsListFragmentProvider

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [
        NewsListFragmentProvider::class
    ])
    abstract fun contributeMainActivity() : MainActivity
}