package com.example.ssutudy.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val accessToken : String?,
    val user: UserDto
)
