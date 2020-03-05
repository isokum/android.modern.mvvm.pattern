package net.sokum.mordern.app.ui.main.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.*
import net.sokum.base.model.BaseViewModel
import net.sokum.base.network.Resource
import net.sokum.mordern.app.data.*
import javax.inject.Inject

class RemoteUsersViewModel @Inject constructor(
    private val repository: RemoteUsersRepository,
    private val localRepository : LocalUsersRepository
) : BaseViewModel() {
    var likeUsers = localRepository.likeUserMap

    private val pageSize = 30
    private var dataSourceFactory : RemoteUsersDataSourceFactory? = null

    fun searchUserPage(query : String) : LiveData<PagedList<UserItem>> {
        dataSourceFactory =
            RemoteUsersDataSourceFactory(
                repository,
                query
            )
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder<Int, UserItem>(dataSourceFactory!!, config).build()
    }

    fun getState(): LiveData<Resource<UserList>> = Transformations.switchMap<RemoteUsersDataSource,
            Resource<UserList>>(dataSourceFactory!!.dataSourceLiveData, RemoteUsersDataSource::state)

}