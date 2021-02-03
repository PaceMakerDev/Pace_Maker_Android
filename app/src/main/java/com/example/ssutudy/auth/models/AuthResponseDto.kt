package com.example.ssutudy.auth.models


import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val accessToken : String?,
    val user: User
)
