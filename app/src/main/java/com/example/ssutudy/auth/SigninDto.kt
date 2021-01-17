package com.example.ssutudy.auth

import kotlinx.serialization.Serializable

@Serializable
data class SigninDto (
    val id : String,
    val password : String
)