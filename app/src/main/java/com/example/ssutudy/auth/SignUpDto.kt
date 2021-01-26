package com.example.ssutudy.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDto (
    val email : String?,
    val major : String?,
    val name : String?,
    val password : String?
)