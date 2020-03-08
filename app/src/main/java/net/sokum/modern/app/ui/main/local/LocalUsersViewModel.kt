package net.sokum.modern.app.ui.main.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.sokum.base.model.BaseViewModel
import net.sokum.modern.app.data.LocalUsersRepository
import net.sokum.modern.app.data.UserItem
import javax.inject.Inject

class LocalUsersViewModel @Inject constructor(
    private val repository : LocalUsersRepository
) : BaseViewModel() {

    var result = MutableLiveData<List<UserItem>>()
    var users = repository.likeUsers

    fun findByLogin(q : String) = repository.search(q)
}