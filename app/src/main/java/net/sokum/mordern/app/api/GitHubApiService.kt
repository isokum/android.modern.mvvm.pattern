package net.sokum.mordern.app.api

import net.sokum.mordern.app.data.UserList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {
    companion object {
        const val ENDPOINT = "https://api.github.com/"
    }

    @GET("/search/users")
    suspend fun searchUsers(@Query("page") page : Int, @Query("q") query : String) : Response<UserList>
}