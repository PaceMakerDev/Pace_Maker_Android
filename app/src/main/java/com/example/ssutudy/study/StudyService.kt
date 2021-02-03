package com.example.ssutudy.study

import com.example.ssutudy.study.models.StudyLog
import com.example.ssutudy.study.models.dto.StudyListDto
import com.example.ssutudy.study.models.dto.StudyLogResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StudyService {
    //공부기록 조회
    @GET("/v1/study-logs")
    fun requestStudyLog(
        @Query("from") startDate : String,
        @Query("to") endDate : String
    ) : Call<StudyLogResponseDto>

    @GET("/v1/study-logs")
    fun requestStudyLog(
        @Query("from") startDate : String,
        @Query("to") endDate : String,
        @Query("studyId") studyIds : String
    ) : Call<StudyLogResponseDto>

    //스터디 목록 조회
    @GET("/v1/studies")
    fun requestStudyList() : Call<StudyListDto>

    @GET("/v1/studies")
    fun requestStudyList(
        @Query("participantId") id : Int
    ) : Call<StudyListDto>

    @GET("/v1/studies")
    fun requestStudyList(
        @Query("search") studyTitle : String
    )

    //스터디 디테일 조회
    @GET("/v1/studies/{studyId}")
    fun requestStudyDetail(
        @Path("studyId") studyId : Int
    )
}