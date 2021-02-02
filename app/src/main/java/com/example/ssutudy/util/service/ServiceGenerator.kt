<<<<<<< HEAD:app/src/main/java/com/example/ssutudy/auth/service/AuthServiceGenerator.kt
package com.example.ssutudy.auth.service
=======
package com.example.ssutudy.util.service
>>>>>>> feature2/home:app/src/main/java/com/example/ssutudy/util/service/ServiceGenerator.kt

import android.text.TextUtils
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ServiceGenerator {
    companion object {
        private final val BASE_URL = "http://13.124.194.199:8080/"
        //private final val BASE_URL = "http://10.0.2.2:8000/"
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
                val interceptor = RequestInterceptor("Bearer " + authToken)

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