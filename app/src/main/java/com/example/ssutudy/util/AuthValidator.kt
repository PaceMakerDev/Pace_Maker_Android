package com.example.ssutudy.util

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

        /*
        fun checkValidation(input : EditText, text : String) : Boolean{
            val view = requireView()
            val i = inputs.indexOf(input)
            var ret = true
            when(input.id) {
                view.auth_edittext_signup_email.id -> {
                    val email = text.trim()
                    ret = checkEmail(input, messages.get(i), email)
                }
                view.auth_edittext_signup_pw.id -> {
                    val password = text
                    ret = checkPassword(input, messages.get(i), password)
                }
                view.auth_edittext_signup_pw_check.id -> {
                    val pw_check = text
                    val password = view.auth_edittext_signup_pw.text.toString()
                    ret = checkPasswordCheck(input, messages.get(i), password, pw_check)
                }
            }
            return ret
        }

        fun checkEmail(input : EditText, message : TextView, email : String) : Boolean{
            if(AuthValidator.isEmailValid(email)) {
                input.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input, null)
                message.text = ""
                message.visibility = View.GONE
                return true
            }
            else {
                input.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input_redline, null)
                message.text = getString(R.string.auth_error_regex_email)
                message.visibility = View.VISIBLE
                return false
            }
        }

        fun checkPassword(input : EditText, message : TextView, password : String) : Boolean{
            if(AuthValidator.isPasswwordValid(password)) {
                input.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input, null)
                message.text = ""
                message.visibility = View.GONE
                return true
            }
            else {
                input.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input_redline, null)
                message.text = getString(R.string.auth_error_regex_password)
                message.visibility = View.VISIBLE
                return false
            }
        }

        fun checkPasswordCheck(input : EditText, message : TextView, password : String, pw_check : String) : Boolean {
            if(!pw_check.equals(password)) {
                input.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input_redline, null)
                message.text = getString(R.string.auth_error_password_check)
                message.visibility = View.VISIBLE
                return false
            } else {
                input.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input, null)
                message.text = ""
                message.visibility = View.GONE
                return true
            }
        }

         */
    }
}