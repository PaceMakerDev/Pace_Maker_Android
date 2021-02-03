package com.example.ssutudy.study.models

import kotlinx.serialization.Serializable

@Serializable
data class StudyLogResponseDto(
    val studyLogs : Array<StudyLog>
)
