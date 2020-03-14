package net.sokum.base.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open abstract class BaseViewModel : ViewModel() {
    val viewModeJob = SupervisorJob()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModeJob)

    override fun onCleared() {
        super.onCleared()

        viewModeJob.cancel()
    }
}

class EmptyViewModel : BaseViewModel()