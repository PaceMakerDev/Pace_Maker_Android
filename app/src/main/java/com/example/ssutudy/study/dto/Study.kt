package com.example.ssutudy.study.dto

import kotlinx.serialization.Serializable

@Serializable
data class Study(
    val title : String?,
    val participantNumber : Int?,
    val tags : List<String>?,
    val attendance : Attendance?,
    val id : Int?
)
