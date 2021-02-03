package com.example.ssutudy.study.models.dto

import com.example.ssutudy.study.models.StudyLog
import kotlinx.serialization.Serializable

@Serializable
data class StudyLogResponseDto(
    val studyLogs : Array<StudyLog>
)
