package net.sokum.mordern.app.ui.main.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import net.sokum.base.model.BaseViewModel
import net.sokum.base.network.Resource
import net.sokum.mordern.app.data.LocalUsersRepository
import net.sokum.mordern.app.data.RemoteUserRepository
import net.sokum.mordern.app.data.UserList
import javax.inject.Inject

class RemoteUsersViewModel @Inject constructor(
    private val repository: RemoteUserRepository,
    private val localRepository : LocalUsersRepository
) : BaseViewModel() {
    var likeUsers = localRepository.likeUserMap

    fun searchUser(query : String) : LiveData<Resource<UserList>> {
        var data = MutableLiveData<Resource<UserList>> ()

        uiScope.launch {
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