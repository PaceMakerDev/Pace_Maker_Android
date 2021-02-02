package com.example.ssutudy.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class SigninDto (
    val email : String,
    val password : String
)