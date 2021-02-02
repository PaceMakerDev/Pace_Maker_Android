<<<<<<< HEAD:app/src/main/java/com/example/ssutudy/auth/dto/AuthResponseDto.kt
package com.example.ssutudy.auth.dto
=======
package com.example.ssutudy.auth.models
>>>>>>> feature2/home:app/src/main/java/com/example/ssutudy/auth/models/AuthResponseDto.kt

import com.example.ssutudy.auth.models.UserDto
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val accessToken : String?,
    val user: UserDto
)
