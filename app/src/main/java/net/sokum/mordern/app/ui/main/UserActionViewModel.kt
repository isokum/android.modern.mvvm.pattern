package net.sokum.mordern.app.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.sokum.base.model.UxEventModel
import net.sokum.mordern.app.module.LikeUserStorage
import net.sokum.mordern.app.data.UserItem
import javax.inject.Inject

class UserActionViewModel @Inject constructor(val localStorage : LikeUserStorage) : ViewModel() {
    companion object {
        const val EVENT_LIKE_CHANGE = "like.change.event"
    }


    var uxEvent = MutableLiveData<UxEventModel<Boolean>>()
    var searchKeyword = MutableLiveData<String>()

    fun doSearch(keyword : String) {
        searchKeyword.value = keyword
    }

    fun likeUser(user: UserItem) {
        localStorage.like(user)

        postLikeChangeEvent()
    }

    fun unLikeUser(user : UserItem) {
        localStorage.unlike(user)

        postLikeChangeEvent()
    }

    fun isLike(user : UserItem) : Boolean {
        return localStorage.isLike(user)
    }

    private fun postLikeChangeEvent() {
        uxEvent.value = UxEventModel(EVENT_LIKE_CHANGE)
    }
}