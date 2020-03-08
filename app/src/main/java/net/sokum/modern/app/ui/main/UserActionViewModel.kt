package net.sokum.modern.app.ui.main

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.sokum.base.model.BaseViewModel
import net.sokum.base.model.UxEventModel
import net.sokum.modern.app.data.LocalUsersRepository
import net.sokum.modern.app.data.UserItem
import javax.inject.Inject

class UserActionViewModel @Inject constructor(
    private val repository: LocalUsersRepository
) : BaseViewModel() {
    var searchKeywordRemote = MutableLiveData<String>()
    var searchKeywordLocal = MutableLiveData<String>()


    fun doSearch(tabIndex : Int, keyword : String) {
        if ( tabIndex == 0 ) {
            searchKeywordRemote.value = keyword
        } else {
            searchKeywordLocal.value = keyword
        }
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