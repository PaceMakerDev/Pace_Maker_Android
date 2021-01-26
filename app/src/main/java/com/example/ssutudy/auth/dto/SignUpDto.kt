package com.example.ssutudy.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDto (
    val email : String?,
    val major : String?,
    val name : String?,
    val password : String?
)