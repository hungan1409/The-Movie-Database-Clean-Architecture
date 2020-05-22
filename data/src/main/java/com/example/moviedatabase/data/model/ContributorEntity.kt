package com.example.moviedatabase.data.model

import com.example.moviedatabase.data.base.EntityMapper
import com.example.moviedatabase.data.base.ModelEntity
import com.example.moviedatabase.domain.model.Contributor
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ContributorEntity(
    @SerializedName("login") val login: String,
    @SerializedName("contributions") val contributions: Int,
    @SerializedName("avatar_url") val avatarUrl: String?
) : ModelEntity()

class ContributorEntityMapper @Inject constructor() : EntityMapper<Contributor, ContributorEntity> {

    override fun mapToDomain(entity: ContributorEntity) = Contributor(
        entity.login, entity.contributions, entity.avatarUrl
    )

    override fun mapToEntity(model: Contributor) = ContributorEntity(
        model.login, model.contributions, model.avatarUrl
    )
}
