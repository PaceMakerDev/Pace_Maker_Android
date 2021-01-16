package com.example.ssutudy.auth

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ssutudy.R
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthActivity : AppCompatActivity() {
    companion object {
        //private final val BASE_URL = "https://skfk0135.stoplight.io/mocks/skfk0135/ssutudy-api-spec/4827703/"
        private final val BASE_URL = "http://10.0.2.2:8000/"
        val TAG = "Auth"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
        val service = retrofit.create(AuthService::class.java)
        // dummy data
        val signup = SignUpDto("jtlsan", "software", "San", "1234")
        val requestSingup = service.signUpUser(signup)
        Log.d(TAG, "request : " + requestSingup.request().url)
        val response = requestSingup.enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                if(response.code() == 201) {
                    //회원가입 성공
                }
                else if (response.code() == 400) {
                    //사용자 입력이 잘못된 항목이 존재("요청 바디 형식이 잘못됐을 경우")
                }
                else if (response.code() == 409){
                    //아이디가 중복일 경우
                }
                else {
                    //code 404
                    //not found.
                }

            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                //응답 받지 못함. 서버다운?
                Log.d(TAG, "Failed")
            }
        })

    }
}