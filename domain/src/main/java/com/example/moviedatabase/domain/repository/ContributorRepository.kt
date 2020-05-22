package com.example.moviedatabase.domain.repository

import com.example.moviedatabase.domain.model.Contributor
import io.reactivex.Observable

interface ContributorRepository {
    // get contributor from a repository on github
    fun getContribution(name: String, owner: String): Observable<List<Contributor>>
}