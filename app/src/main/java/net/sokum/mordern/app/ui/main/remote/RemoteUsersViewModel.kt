package net.sokum.mordern.app.ui.main.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import net.sokum.mordern.app.base.network.Resource
import net.sokum.mordern.app.data.RemoteUserRepository
import net.sokum.mordern.app.data.UserList
import javax.inject.Inject

class RemoteUsersViewModel @Inject constructor(private val repository: RemoteUserRepository) : ViewModel() {
    private val viewModeJob = SupervisorJob()
    private val mainScope = CoroutineScope(Dispatchers.Main + viewModeJob)

    fun searchUser(query : String) : LiveData<Resource<UserList>> {
        var data = MutableLiveData<Resource<UserList>> ()

        mainScope.launch {
            data.postValue(Resource.Loading("Loading"))

            withContext(Dispatchers.Default) {
                var resource = repository.searchUsers(query)
                data.postValue(resource)
            }
        }
        return data
    }

    override fun onCleared() {
        super.onCleared()

        viewModeJob.cancel()
    }
}