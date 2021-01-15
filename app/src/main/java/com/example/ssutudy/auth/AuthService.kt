package com.example.ssutudy.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/v1/auth/sign-up")
    fun signUpUser(@Body signUpDto: SignUpDto) : Call<UserDto>
}