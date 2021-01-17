package com.example.ssutudy.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.ssutudy.R
import com.example.ssutudy.auth.enums.AuthInputs
import kotlinx.android.synthetic.main.fragment_auth_login.view.*
import kotlinx.android.synthetic.main.fragment_auth_signup.view.*

class AuthSignupFragment : Fragment() {
    companion object {
        lateinit private var authActivity: AuthActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_signup, container, false)

        view.auth_button_signin_from_signup.setOnClickListener(SignupClickListener(view))

        view.auth_button_student_card.setOnClickListener {

        }

        view.auth_edittext_signup_pw_check.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE) {
                view.auth_button_signin_from_signup.callOnClick()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        return view
    }

    inner class SignupClickListener(val view : View) : View.OnClickListener {
        override fun onClick(p0: View?) {
            var isAllFilled = false

            // 모든 input box 빨간선 해제
            view.auth_edittext_signup_name.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input, null)
            view.auth_edittext_signup_major.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input, null)
            view.auth_edittext_signup_id.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input, null)
            view.auth_edittext_signup_pw.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input, null)
            view.auth_edittext_signup_pw_check.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input, null)

            // 빈 input box들 빨간선 그리기
            when(isEditTextFilled()) {
                AuthInputs.NAME -> {
                    view.auth_edittext_signup_name.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input_redline, null)
                }
                AuthInputs.MAJOR -> {
                    view.auth_edittext_signup_major.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input_redline, null)
                }
                AuthInputs.ID -> {
                    view.auth_edittext_signup_id.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input_redline, null)
                }
                AuthInputs.PASSWORD -> {
                    view.auth_edittext_signup_pw.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input_redline, null)
                }
                AuthInputs.PASSWORDCHECK -> {
                    view.auth_edittext_signup_pw_check.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input_redline, null)
                }
                AuthInputs.NOTHING -> isAllFilled = true
            }

            if(isAllFilled) {
                val id = view.auth_edittext_signup_id.text.toString()
                val name = view.auth_edittext_signup_name.text.toString()
                val major = view.auth_edittext_signup_major.text.toString()
                val password = view.auth_edittext_signup_pw.text.toString()
                val signupDto = SignUpDto(id, major, name, password)
                authActivity.requestSignup(signupDto)
            }
        }

        fun isEditTextFilled() : AuthInputs {
            if(view.auth_edittext_signup_name.text.isBlank()) return AuthInputs.NAME
            if(view.auth_edittext_signup_major.text.isBlank()) return AuthInputs.MAJOR
            if(view.auth_edittext_signup_id.text.isBlank()) return AuthInputs.ID
            if(view.auth_edittext_signup_pw.text.isBlank()) return AuthInputs.PASSWORD
            if(view.auth_edittext_signup_pw_check.text.isBlank()) return AuthInputs.PASSWORDCHECK
            return AuthInputs.NOTHING
        }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authActivity = activity as AuthActivity
    }
}