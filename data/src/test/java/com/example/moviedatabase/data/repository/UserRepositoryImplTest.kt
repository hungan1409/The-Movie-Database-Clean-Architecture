package com.example.moviedatabase.data.repository

import com.example.moviedatabase.data.model.UserEntityMapper
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class UserRepositoryImplTest {
    private lateinit var userRepositoryImpl: UserRepositoryImpl

    private val userApiMock = mock(UserApi::class.java)
    private val appDatabaseMock = mock(AppDatabase::class.java)
    private val userEntityMapper = UserEntityMapper()

    @Before
    fun setup() {
        userRepositoryImpl = UserRepositoryImpl(userApiMock, appDatabaseMock, userEntityMapper)
    }

    @Test
    fun signSuccess() {
        val username = "hoanbn88"
        val pass = "abc123"
        `when`(userApiMock.signin(username, pass)).thenReturn(Completable.complete())
    }
}
