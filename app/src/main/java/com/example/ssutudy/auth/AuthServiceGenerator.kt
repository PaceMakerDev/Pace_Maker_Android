package com.example.ssutudy.auth

import android.text.TextUtils
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class AuthServiceGenerator {
    companion object {
        //private final val BASE_URL = "https://skfk0135.stoplight.io/mocks/skfk0135/ssutudy-api-spec/4827703/"
        private final val BASE_URL = "http://10.0.2.2:8000/"
        val httpClient = OkHttpClient.Builder()
        private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))

        private var retrofit = builder.build()

        fun <S> createService(serviceClass : Class<S>) : S {
            return createService(serviceClass, null)
        }

        fun <S> createService(serviceClass : Class<S>, authToken : String?) : S{
            if(!TextUtils.isEmpty(authToken)) {
                val interceptor = AuthInterceptor("Bearer " + authToken)

                if(!httpClient.interceptors().contains(interceptor)) {
                    httpClient.addInterceptor(interceptor)

                    builder.client(httpClient.build())
                    retrofit = builder.build()
                }
            }

            return retrofit.create(serviceClass)
        }
    }
}