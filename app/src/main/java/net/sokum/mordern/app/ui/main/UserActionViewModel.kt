package net.sokum.mordern.app.ui.main

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.sokum.base.model.BaseViewModel
import net.sokum.base.model.UxEventModel
import net.sokum.mordern.app.data.LocalUsersRepository
import net.sokum.mordern.app.data.UserItem
import javax.inject.Inject

class UserActionViewModel @Inject constructor(
    private val repository: LocalUsersRepository
) : BaseViewModel() {
    var searchKeyword = MutableLiveData<String>()

    fun doSearch(keyword : String) {
        searchKeyword.value = keyword
    }

    fun likeUser(user: UserItem) {
        uiScope.launch {
            repository.insert(user)
        }
    }

    fun unLikeUser(user : UserItem) {
        uiScope.launch {
            repository.delete(user)
        }
    }
}