package com.example.moviedatabase.data

import com.example.moviedatabase.data.local.db.AppDatabase
import com.example.moviedatabase.data.model.UserEntity
import com.example.moviedatabase.data.model.UserEntityMapper
import com.example.moviedatabase.data.remote.api.UserApi
import com.example.moviedatabase.domain.model.User
import com.example.moviedatabase.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val appDatabase: AppDatabase,
    private val mapper: UserEntityMapper
) : UserRepository {
    override fun signin(userName: String, password: String): Completable {
        return userApi.signin(userName, password)
    }

    override fun getUser(id: String, fromServer: Boolean): Single<User> = when (fromServer) {
        false -> appDatabase.userDao().findById(id).map { mapper.mapToDomain(it) }
        true -> userApi.getUser(id)
            .map { mapper.mapToDomain(it) }
            .onErrorResumeNext(getUser(id, false))
    }

    override fun saveUser(user: User) {
        return set(mapper.mapToEntity(user))
    }

    private fun set(userEntity: UserEntity) = appDatabase.userDao().insert(userEntity)
}