package com.example.ssutudy.auth.service

<<<<<<< HEAD:app/src/main/java/com/example/ssutudy/auth/service/AuthService.kt
import com.example.ssutudy.auth.dto.AuthResponseDto
import com.example.ssutudy.auth.dto.SignUpDto
import com.example.ssutudy.auth.dto.SigninDto
=======
import com.example.ssutudy.auth.models.AuthResponseDto
import com.example.ssutudy.auth.models.SignUpDto
import com.example.ssutudy.auth.models.SigninDto
>>>>>>> feature2/home:app/src/main/java/com/example/ssutudy/auth/AuthService.kt
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/v1/auth/sign-up")
    fun signupUser(@Body signUpDto: SignUpDto) : Call<AuthResponseDto>

    @POST("/v1/auth/sign-in")
    fun signinUser(@Body signinDto: SigninDto) : Call<AuthResponseDto>
}