package net.sokum.mordern.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.sokum.mordern.app.ui.NewsListFragment
import net.sokum.mordern.app.ui.NewsListFragmentModule

@Module
abstract class ActivityBuilder {


    @FragmentScope @ContributesAndroidInjector(modules = [
        NewsListFragmentModule::class
    ])
    abstract fun contributeNewsListFragment() : NewsListFragment

}