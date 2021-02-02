package com.example.ssutudy.study

import com.example.ssutudy.study.models.StudyLog
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudyService {
    @GET("/v1/study-logs")
    fun requestStudyLog(
        @Query("from") startDate : String,
        @Query("to") endDate : String
    ) : Call<StudyLog>

    @GET("/v1/study-logs")
    fun requestStudyLogByStudyId(
        @Query("from") startDate : String,
        @Query("to") endDate : String,
        @Query("studyId") studyIds : String
    ) : Call<StudyLog>
}