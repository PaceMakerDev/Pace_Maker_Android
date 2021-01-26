package com.example.ssutudy.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    val email : String?,
    val name : String?,
    val major : String?
)