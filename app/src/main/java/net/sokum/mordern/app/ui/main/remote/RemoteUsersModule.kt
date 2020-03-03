package net.sokum.mordern.app.ui.main.remote

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.sokum.base.di.ViewModelKey
import net.sokum.mordern.app.ui.main.UserActionViewModel

@Module
abstract class RemoteUsersModule {
    @Binds
    @IntoMap
    @ViewModelKey(RemoteUsersViewModel::class)
    abstract fun bindRemoteUsersViewModel(viewModel: RemoteUsersViewModel): ViewModel
}