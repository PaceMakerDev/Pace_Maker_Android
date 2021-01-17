package com.example.ssutudy.auth

import android.graphics.Point
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ssutudy.R
import kotlinx.android.synthetic.main.fragment_auth_main.*
import kotlinx.android.synthetic.main.fragment_auth_main.view.*

class AuthMainFragment : Fragment() {
    companion object {
        val TAG = "Auth"
        lateinit private var authActivity : AuthActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_main, container, false)

        val display = requireContext().display
        val size = Point()
        display!!.getRealSize(size)
        val width = size.x
        val height = size.y
        Log.d(TAG, "width :${width}, height : ${height}")


        view.auth_button_login.setOnClickListener {
            authActivity.setFragment(AuthFragments.LOGIN)
        }

        view.auth_button_signup.setOnClickListener {
            authActivity.setFragment(AuthFragments.SIGNUP)
        }


        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authActivity = activity as AuthActivity
    }
}