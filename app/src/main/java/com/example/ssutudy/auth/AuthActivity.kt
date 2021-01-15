package com.example.ssutudy.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ssutudy.R
import retrofit2.Retrofit

class AuthActivity : AppCompatActivity() {
    companion object {
        private final val BASE_URL = "localhost:8000//"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)

    }
}