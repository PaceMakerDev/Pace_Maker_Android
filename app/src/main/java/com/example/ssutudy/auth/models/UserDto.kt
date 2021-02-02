package com.example.ssutudy.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    val id : Int?,
    val name : String?,
    val major : String?,
    val email : String?
)