package com.example.moviedatabase.data

import com.example.moviedatabase.data.model.UserEntity
import com.example.moviedatabase.domain.model.User

fun createUserEntity(): UserEntity = UserEntity(
    "1",
    "Bach",
    "bachhoan88",
    "hoanbn88@gmail.com",
    "0904576359  ",
    "Tu Liem - Ha Noi"
)

fun createUser(): User = User(
    "2",
    "Hoan",
    "hoanbn88",
    "bach.ngoc.hoai@framgia.com",
    "0123456789",
    "Thanh Liem - Ha Nam"
)