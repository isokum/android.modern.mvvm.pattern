package net.sokum.mordern.app.data

import net.sokum.mordern.app.api.GitHubApiService
import net.sokum.base.network.Resource
import net.sokum.base.network.ResponseHandler
import java.lang.Exception
import javax.inject.Inject

class RemoteUsersRepository @Inject constructor(
    private val service : GitHubApiService
) {
    suspend fun searchUsers(query : String) : Resource<UserList> {
        try {
            val response = service.searchUsers(query)

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