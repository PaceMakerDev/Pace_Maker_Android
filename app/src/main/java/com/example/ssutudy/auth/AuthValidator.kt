package com.example.ssutudy.auth

class AuthValidator {
    companion object {
        fun isEmailValid(email : String) : Boolean {
            val emailValidation = Regex("^[_A-Za-z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-z]+$")
            if(email.matches(emailValidation))
                return true
            else
                return false
        }

        fun isPasswwordValid(password : String) : Boolean {
            val passwordValidation = Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{7,19}.$")
            if(password.matches(passwordValidation))
                return true
            else
                return false
        }
    }
}