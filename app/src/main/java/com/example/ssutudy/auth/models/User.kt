package com.example.ssutudy.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id : Int?,
    val name : String?,
    val major : String?,
    val email : String?
)