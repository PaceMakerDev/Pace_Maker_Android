package com.example.ssutudy.auth

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
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
import java.security.CryptoPrimitive
import java.security.DigestException
import java.security.MessageDigest


class AuthActivity : AppCompatActivity() {
    companion object {
        //private final val BASE_URL = "http://13.124.194.199:8080/"
        //private val BASE_URL = "http://10.0.2.2:8000/"
        val TAG = "Auth"
        lateinit private var mainFragment : AuthMainFragment
        lateinit private var retrofit: Retrofit
        lateinit private var service : AuthService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        mainFragment = AuthMainFragment()

        setFragment(AuthFragments.MAIN)

        // retorfit setting
        /*
        val contentType = "application/json".toMediaType()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
        service = retrofit.create(AuthService::class.java)

         */
        service = AuthServiceGenerator.createService(AuthService::class.java)


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

    fun startCameraActivity() {
        val intent = Intent(this, AuthCameraActivity::class.java)
        startActivity(intent)
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
                            editor.putString("user_email", it.user.email)
                            editor.putString("user_name", it.user.name)
                            editor.putString("user_major", it.user.major)
                        }
                        editor.apply()
                        Log.d(TAG, "login successful")
                    }
                    400 -> {
                        //요청바디형식이 잘못됨
                        Log.d(TAG, "요청바디형식")
                    }
                    403 -> {
                        //아이디나 비밀번호 틀림
                        Log.d(TAG, "아이디 비밀번호")
                    }
                    else -> {
                        //서버 다운
                        Log.d(TAG, "404" + response.code())
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
                            editor.putString("user_email", it.user.email)
                            editor.putString("user_name", it.user.name)
                            editor.putString("user_major", it.user.major)
                        }
                        editor.apply()
                        Log.d(TAG, "signup succesfsul")
                    }
                    400 -> {
                        // 요청 바디 형식 오류
                        Log.d(TAG, "요청바디형식 in signup")
                        Log.d(TAG, response.raw().message)
                    }
                    409 -> {
                        //아이디 중복
                        Log.d(TAG, "아이디 비밀번호")
                    }
                    else -> {
                        //서버다운
                        Log.d(TAG, "error code : " + response.code())
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponseDto>, t: Throwable) {
                //서버다운?
                Log.d(TAG, t.localizedMessage)
            }
        })
    }

    fun encryptSHA256(pw : String) : String{
        val hash : ByteArray
        try {
            val sh = MessageDigest.getInstance("SHA-256")
            sh.update(pw.toByteArray())
            hash = sh.digest()
        } catch (e : CloneNotSupportedException) {
            throw DigestException("couldn't make digest")
        }
        val sb = StringBuilder()
        for(b in hash) {
            sb.append(String.format("%02x", b))
        }
        return sb.toString()
    }
}