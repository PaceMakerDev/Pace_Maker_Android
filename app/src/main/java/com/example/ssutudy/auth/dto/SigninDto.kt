package com.example.ssutudy.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class SigninDto (
    val email : String,
    val password : String
)