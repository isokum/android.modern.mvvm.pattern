package net.sokum.modern.app.ui.main.local

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.sokum.base.di.ViewModelKey
import net.sokum.modern.app.ui.main.UserActionViewModel

@Module
abstract class LocalUsersModule {
    @Binds
    @IntoMap
    @ViewModelKey(LocalUsersViewModel::class)
    abstract fun bindLocalUsersViewModel(viewModel: LocalUsersViewModel): ViewModel
}