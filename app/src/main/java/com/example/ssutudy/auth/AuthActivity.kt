package com.example.ssutudy.auth

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ssutudy.R
import com.example.ssutudy.auth.enums.AuthFragments
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
        lateinit private var mainFragment : AuthMainFragment
        lateinit private var retrofit: Retrofit
        lateinit private var service : AuthService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        mainFragment = AuthMainFragment()

        setFragment(AuthFragments.MAIN)

        // retorfit setting
        val contentType = "application/json".toMediaType()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
        service = retrofit.create(AuthService::class.java)


    }

    fun setFragment(frag : AuthFragments) {
        supportFragmentManager.beginTransaction().apply {


            when(frag) {
                AuthFragments.MAIN -> {
                    replace(R.id.auth_main_frame, mainFragment)
                    commit()
                }
                AuthFragments.LOGIN -> {
                    setCustomAnimations(R.anim.from_right_to_center, R.anim.from_center_to_left, R.anim.from_left_to_center, R.anim.from_center_to_right)
                    addToBackStack(null)
                    setReorderingAllowed(true)
                    replace(R.id.auth_main_frame, AuthLoginFragment())
                    commit()
                }
                AuthFragments.SIGNUP -> {
                    setCustomAnimations(R.anim.from_right_to_center, R.anim.from_center_to_left, R.anim.from_left_to_center, R.anim.from_center_to_right)
                    addToBackStack(null)
                    setReorderingAllowed(true)
                    replace(R.id.auth_main_frame, AuthSignupFragment())
                    commit()
                }
            }
        }
    }

    fun requestSignin(signinDto: SigninDto) {
        val requestLogin = service.signinUser(signinDto)
        requestLogin.enqueue(object : Callback<AuthResponseDto> {
            override fun onResponse(call: Call<AuthResponseDto>, response: Response<AuthResponseDto>) {
                when(response.code()) {
                    200 -> {
                        //로그인성공
                        val editor = getSharedPreferences("auth", MODE_PRIVATE).edit()
                        response.body()!!.let {
                            editor.putString("access_token", it.accessToken)
                            editor.putString("user_id", it.user.id)
                            editor.putString("user_name", it.user.name)
                            editor.putString("user_major", it.user.major)
                        }
                        editor.apply()
                    }
                    400 -> {
                        //요청바디형식이 잘못됨
                    }
                    403 -> {
                        //아이디나 비밀번호 틀림
                    }
                    else -> {
                        //서버 다운
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponseDto>, t: Throwable) {
                Log.d(TAG, "onFailure")
                Log.d(TAG, t.localizedMessage)
            }
        })
    }

    fun requestSignup(signupDto : SignUpDto) {
        val requestSignup = service.signupUser(signupDto)
        requestSignup.enqueue(object : Callback<AuthResponseDto> {
            override fun onResponse(
                call: Call<AuthResponseDto>,
                response: Response<AuthResponseDto>
            ) {
                when (response.code()) {
                    201 -> {
                        //회원가입성공
                        val editor = getSharedPreferences("auth", MODE_PRIVATE).edit()
                        response.body()!!.let {
                            editor.putString("access_token", it.accessToken)
                            editor.putString("user_id", it.user.id)
                            editor.putString("user_name", it.user.name)
                            editor.putString("user_major", it.user.major)
                        }
                        editor.apply()
                    }
                    400 -> {
                        // 요청 바디 형식 오류
                    }
                    409 -> {
                        //아이디 중복
                    }
                    else -> {
                        //서버다운
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponseDto>, t: Throwable) {
                //서버다운?
            }
        })
    }
}