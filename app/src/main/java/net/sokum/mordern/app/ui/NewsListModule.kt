package net.sokum.mordern.app.ui

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import net.sokum.mordern.app.data.NewsListViewModel
import net.sokum.mordern.app.di.ViewModelKey


@Module
abstract class NewsListFragmentProvider {
    @ContributesAndroidInjector(modules = [NewsListModule::class])
    abstract fun contributeNewsListFragment() : NewsListFragment
}


@Module
abstract class NewsListModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    internal abstract fun postListViewModel(viewModel: NewsListViewModel): ViewModel
}