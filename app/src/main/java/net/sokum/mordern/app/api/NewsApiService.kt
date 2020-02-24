package net.sokum.mordern.app.api

import net.sokum.mordern.app.data.TopHeadLines
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface NewsApiService {
    companion object {
        const val ENDPOINT = "http://newsapi.org/"
    }

    @GET("/v2/top-headlines")
    suspend fun topHeadLines(@Query("country") country : String? = "kr") : Response<TopHeadLines>
}