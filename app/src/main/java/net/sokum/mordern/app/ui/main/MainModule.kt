package net.sokum.mordern.app.ui.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.sokum.base.di.ViewModelKey

@Module
abstract class MainModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserActionViewModel::class)
    abstract fun bindUserActionViewModel(viewModel: UserActionViewModel): ViewModel
}