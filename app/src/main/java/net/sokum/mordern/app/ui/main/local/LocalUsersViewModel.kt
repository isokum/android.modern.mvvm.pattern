package net.sokum.mordern.app.ui.main.local

import androidx.lifecycle.MutableLiveData
import net.sokum.base.model.BaseViewModel
import net.sokum.mordern.app.data.LocalUsersRepository
import net.sokum.mordern.app.data.UserItem
import javax.inject.Inject

class LocalUsersViewModel @Inject constructor(
    repository : LocalUsersRepository
) : BaseViewModel() {

    var result = MutableLiveData<List<UserItem>>()
    var users = repository.likeUsers
}