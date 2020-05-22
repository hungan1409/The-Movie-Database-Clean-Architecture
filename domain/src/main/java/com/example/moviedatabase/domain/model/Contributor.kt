package com.example.moviedatabase.domain.model

data class Contributor(val login: String, val contributions: Int, val avatarUrl: String?) : Model()
