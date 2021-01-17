package com.example.ssutudy.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.example.ssutudy.R
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

        view.auth_button_signin_from_signup.setOnClickListener {

        }

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authActivity = activity as AuthActivity
    }
}