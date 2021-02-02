<<<<<<< HEAD:app/src/main/java/com/example/ssutudy/auth/dto/SigninDto.kt
package com.example.ssutudy.auth.dto
=======
package com.example.ssutudy.auth.models
>>>>>>> feature2/home:app/src/main/java/com/example/ssutudy/auth/models/SigninDto.kt

import kotlinx.serialization.Serializable

@Serializable
data class SigninDto (
    val email : String,
    val password : String
)