package com.example.moviedatabase.data.model

import androidx.room.Entity
import com.example.moviedatabase.data.base.EntityMapper
import com.example.moviedatabase.data.base.ModelEntity
import com.example.moviedatabase.domain.model.User
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

@Entity(tableName = "user", primaryKeys = ["id"])
data class UserEntity(
    @field: SerializedName("id") val id: String,
    @field: SerializedName("name") val name: String,
    @field: SerializedName("username") val username: String,
    @field: SerializedName("email") val email: String,
    @field: SerializedName("phone") val phone: String,
    @field: SerializedName("address") val address: String
) : ModelEntity()

class UserEntityMapper @Inject constructor() : EntityMapper<User, UserEntity> {
    override fun mapToDomain(entity: UserEntity): User = User(
        id = entity.id,
        name = entity.name,
        username = entity.username,
        email = entity.email,
        phone = entity.phone,
        address = entity.address
    )

    override fun mapToEntity(model: User): UserEntity = UserEntity(
        id = model.id,
        name = model.name,
        username = model.username,
        email = model.email,
        phone = model.phone,
        address = model.address
    )
}
