package com.example.ssutudy.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ssutudy.R
import kotlinx.android.synthetic.main.fragment_auth_login.view.*

class AuthLoginFragment : Fragment() {
    companion object {
        lateinit private var authActivity: AuthActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_login, container, false)

        view.auth_button_signin.setOnClickListener {
            val id = view.auth_edittext_id.text.toString()
            val password = view.auth_edittext_pw.text.toString()
            val signinDto = SigninDto(id, password)
            authActivity.requestSignin(signinDto)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authActivity = activity as AuthActivity
    }

}