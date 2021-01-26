package com.example.ssutudy.auth.service

import com.example.ssutudy.auth.dto.AuthResponseDto
import com.example.ssutudy.auth.dto.SignUpDto
import com.example.ssutudy.auth.dto.SigninDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/v1/auth/sign-up")
    fun signupUser(@Body signUpDto: SignUpDto) : Call<AuthResponseDto>

    @POST("/v1/auth/sign-in")
    fun signinUser(@Body signinDto: SigninDto) : Call<AuthResponseDto>
}