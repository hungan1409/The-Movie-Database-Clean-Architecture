package com.example.moviedatabase.data

import com.example.moviedatabase.data.model.ContributorEntityMapper
import com.example.moviedatabase.data.remote.api.ContributorApi
import com.example.moviedatabase.domain.model.Contributor
import com.example.moviedatabase.domain.repository.ContributorRepository
import io.reactivex.Observable
import javax.inject.Inject

class ContributorRepositoryImpl @Inject constructor(
    private val contributorApi: ContributorApi,
    private val contributorEntityMapper: ContributorEntityMapper
) : ContributorRepository {

    override fun getContribution(name: String, owner: String): Observable<List<Contributor>> {
        return contributorApi.getContributors(owner, name)
            .map { it.map { contributorEntityMapper.mapToDomain(it) } }
    }
}