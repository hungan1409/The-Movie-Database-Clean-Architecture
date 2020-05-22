package com.example.moviedatabase.data.remote.api

import com.example.moviedatabase.data.model.ContributorEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ContributorApi {

    @GET("repos/{owner}/{name}/contributors")
    fun getContributors(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): Observable<List<ContributorEntity>>
}