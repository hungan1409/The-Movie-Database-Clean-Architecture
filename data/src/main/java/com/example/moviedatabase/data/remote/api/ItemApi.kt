package com.example.moviedatabase.data.remote.api

import com.example.moviedatabase.data.remote.response.SearchRepoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemApi {

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String, @Query("page") page: Int): Single<SearchRepoResponse>
}