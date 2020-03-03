package net.sokum.mordern.app.ui.main.local

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.sokum.base.model.BaseViewModel
import net.sokum.mordern.app.module.LikeUserStorage
import net.sokum.mordern.app.data.UserItem
import javax.inject.Inject

class LocalUsersViewModel @Inject constructor(val localStorage : LikeUserStorage) : BaseViewModel() {
    var result = MutableLiveData<List<UserItem>>()

    fun searchUser(query : String) {
        var users = localStorage.searchUsers(query)

        result.value = users
    }
}