package com.example.ssutudy.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    val id : String?,
    val name : String?,
    val major : String?
)