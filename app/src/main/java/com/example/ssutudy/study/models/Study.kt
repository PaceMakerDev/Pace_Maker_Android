package com.example.ssutudy.study.models

import com.example.ssutudy.auth.models.User
import kotlinx.serialization.Serializable

@Serializable
data class Study(
    val title : String?,
    val participantNumber : Int?,
    val tags : Array<Tag>?,
    val attendance : Attendance?,
    val id : Int?,
    val participantLimit : Int = 0,
    val description : String = "",
    val owner : User? = null
)
