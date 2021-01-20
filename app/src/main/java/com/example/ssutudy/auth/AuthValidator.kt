package com.example.ssutudy.auth

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.ssutudy.R
import kotlinx.android.synthetic.main.fragment_auth_signup.view.*

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