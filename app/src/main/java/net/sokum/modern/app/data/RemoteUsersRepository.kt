package net.sokum.modern.app.data

import net.sokum.modern.app.api.GitHubApiService
import net.sokum.base.network.Resource
import net.sokum.base.network.ResponseHandler
import java.lang.Exception
import javax.inject.Inject

class RemoteUsersRepository @Inject constructor(
    private val service : GitHubApiService
) {
    suspend fun searchUsers(page: Int, query : String) : Resource<UserList> {
        try {
            val response = service.searchUsers(page, query)

            if ( response.isSuccessful ) {
                return ResponseHandler.handleSuccess(response.body()!!, response.headers())
            } else {
                return ResponseHandler.handleFailure(response.code(), response.errorBody())
            }
        } catch (e : Exception) {
            return ResponseHandler.handleExecption(e)
        }
    }
}