package com.example.ssutudy.study.dto

import kotlinx.serialization.Serializable

@Serializable
data class StudyLog(
    val weekday : String?,
    val date : String?,
    val action : String?,
    val time : String?,
    val study : Study?,
    val id : Int?
)
