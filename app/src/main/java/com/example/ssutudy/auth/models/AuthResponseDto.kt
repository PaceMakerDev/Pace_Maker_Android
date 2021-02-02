package com.example.ssutudy.auth.models


import com.example.ssutudy.auth.models.UserDto
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val accessToken : String?,
    val user: UserDto
)
