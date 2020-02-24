package net.sokum.mordern.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.sokum.mordern.app.ui.NewsListFragment

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeNewsListFragment() : NewsListFragment
}