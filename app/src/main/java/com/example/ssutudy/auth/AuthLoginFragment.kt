package com.example.ssutudy.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.ssutudy.R
<<<<<<< HEAD
import com.example.ssutudy.auth.dto.SigninDto
=======
import com.example.ssutudy.auth.models.SigninDto
import com.example.ssutudy.util.AuthValidator
>>>>>>> feature2/home
import kotlinx.android.synthetic.main.fragment_auth_login.*
import kotlinx.android.synthetic.main.fragment_auth_login.view.*

class AuthLoginFragment : Fragment() {
    lateinit private var authActivity: AuthActivity
    private val inputs : ArrayList<EditText> = arrayListOf()
    private val messages : ArrayList<TextView> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_login, container, false)
        initialSetUp(view)

        view.auth_login_edittext_email.addTextChangedListener {
            checkEmail(view.auth_login_edittext_email, view.auth_login_textview_error_email)
        }

        view.auth_button_signin.setOnClickListener(SignupClickListener(view))

        return view
    }

    private fun initialSetUp(view : View) {
        //edittext들 arraylist에 넣어주기
        inputs.add(view.auth_login_edittext_email)
        inputs.add(view.auth_login_edittext_pw)

        //edittext들 arrayList에 넣기
        messages.add(view.auth_login_textview_error_email)
        messages.add(view.auth_login_textview_error_pw)

        view.auth_login_edittext_pw.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE) {
                view.auth_button_signin.callOnClick()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun checkEmail(input : EditText, message : TextView) : Boolean{
        val email = input.text.toString().trim()
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

    private inner class SignupClickListener(val view : View) : View.OnClickListener {
        var isGood = true
        override fun onClick(p0: View?) {
            resetInputs()
            validateInputs()

            //startActivity(Intent(context, MainActivity::class.java))

            if(isGood) {
                val email = view.auth_login_edittext_email.text.toString()
                val password = authActivity.encryptSHA256(view.auth_login_edittext_pw.text.toString())
                val signInDto = SigninDto(email, password)
                authActivity.requestSignin(signInDto)
            }
        }

        fun validateInputs() {
            if(!checkEmail(view.auth_login_edittext_email, view.auth_login_textview_error_email))
                isGood = false
            checkBlankException()
        }

        fun checkBlankException() {
            for((i, input) in inputs.withIndex()) {
                if (input.text.isBlank()) {
                    isGood = false
                    val message = messages.get(i)
                    input.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input_redline, null)
                    when(message.id) {
                        R.id.auth_login_textview_error_email -> message.text = getString(R.string.auth_error_blank_signup_email)
                        R.id.auth_login_textview_error_pw -> message.text = getString(R.string.auth_error_blank_signup_pw)
                    }
                    message.visibility = View.VISIBLE
                }
            }
        }

        fun resetInputs() {
            isGood = true
            val strings = ArrayList<String>()
            strings.add(getString(R.string.auth_email))
            strings.add(getString(R.string.auth_pw))

            for(i in inputs.indices) {
                inputs.get(i).background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input, null)
                inputs.get(i).hint = strings.get(i)
            }
            for(message in messages) {
                message.text = ""
                message.visibility = View.GONE
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authActivity = activity as AuthActivity
    }



}