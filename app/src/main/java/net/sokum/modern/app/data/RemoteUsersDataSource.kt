package net.sokum.modern.app.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.sokum.base.network.Resource

class RemoteUsersDataSource constructor(
    private val repository: RemoteUsersRepository,
    private val query : String
) : PageKeyedDataSource<Int, UserItem>() {
    var state = MutableLiveData<Resource<UserList>> ()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UserItem>
    ) {
        state.postValue(Resource.Loading())

        CoroutineScope(Dispatchers.Default).launch {
            var result : Resource<UserList> = repository.searchUsers(1, query)

            when(result) {
                is Resource.Success -> {
                    callback.onResult(result.data.items, null, 2)
                    state.postValue(Resource.Done())
                }
                is Resource.Error -> {
                    state.postValue(result)
                }
            }
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserItem>) {
        state.postValue(Resource.Loading())

        CoroutineScope(Dispatchers.Default).launch {
            var result : Resource<UserList> = repository.searchUsers(params.key, query)

            when(result) {
                is Resource.Success -> {
                    callback.onResult(result.data.items, params.key + 1)
                    state.postValue(Resource.Done())
                }
                is Resource.Error -> {
                    state.postValue(result)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserItem>) {
    }
}

class RemoteUsersDataSourceFactory(
    private val repository: RemoteUsersRepository,
    private val query : String
) : DataSource.Factory<Int, UserItem>() {
    val dataSourceLiveData = MutableLiveData<RemoteUsersDataSource>()

    override fun create(): DataSource<Int, UserItem> {
        val dataSource =
            RemoteUsersDataSource(repository, query)

        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}