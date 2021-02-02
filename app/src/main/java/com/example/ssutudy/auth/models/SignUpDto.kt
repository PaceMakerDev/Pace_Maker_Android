<<<<<<< HEAD:app/src/main/java/com/example/ssutudy/auth/dto/SignUpDto.kt
package com.example.ssutudy.auth.dto
=======
package com.example.ssutudy.auth.models
>>>>>>> feature2/home:app/src/main/java/com/example/ssutudy/auth/models/SignUpDto.kt

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDto (
    val email : String?,
    val major : String?,
    val name : String?,
    val password : String?
)