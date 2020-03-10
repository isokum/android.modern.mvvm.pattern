package net.sokum.modern.app.ui.main

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.sokum.base.model.BaseViewModel
import net.sokum.modern.app.data.LocalUsersRepository
import net.sokum.modern.app.data.UserItem
import javax.inject.Inject

class UserActionViewModel @Inject constructor(
    private val repository: LocalUsersRepository
) : BaseViewModel() {
    var searchKeywordRemote = MutableLiveData<String>()
    var searchKeywordLocal = MutableLiveData<String>()

    var likeUsersMap = mapOf<Long, UserItem>()


    fun doSearch(tabIndex : Int, keyword : String) {
        if ( tabIndex == 0 ) {
            searchKeywordRemote.value = keyword
        } else {
            searchKeywordLocal.value = keyword
        }
    }

    fun isLike(user : UserItem) : Boolean {
        return likeUsersMap.containsKey(user.id)
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