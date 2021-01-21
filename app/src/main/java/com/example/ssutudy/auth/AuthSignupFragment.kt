package com.example.ssutudy.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.ssutudy.R
import kotlinx.android.synthetic.main.fragment_auth_login.view.*
import kotlinx.android.synthetic.main.fragment_auth_signup.*
import kotlinx.android.synthetic.main.fragment_auth_signup.view.*
import kotlinx.android.synthetic.main.fragment_auth_signup.view.auth_signup_textview_error_email

class AuthSignupFragment : Fragment() {

    lateinit private var authActivity: AuthActivity
    private var inputs : ArrayList<EditText> = arrayListOf()
    private var messages : ArrayList<TextView> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_signup, container, false)
        initialSetUp(view)
        setFocusChangeListener(view)
        view.auth_button_signin_from_signup.setOnClickListener(SignupClickListener(view))


        view.auth_button_student_card.setOnClickListener {
            //카메라 인식
        }


        return view
    }

    fun initialSetUp(view : View) {
        //edittext들 arraylist에 넣어주기
        inputs.add(view.auth_edittext_signup_name)
        inputs.add(view.auth_edittext_signup_major)
        inputs.add(view.auth_edittext_signup_email)
        inputs.add(view.auth_edittext_signup_pw)
        inputs.add(view.auth_edittext_signup_pw_check)

        //edittext들 arrayList에 넣기
        messages.add(view.auth_signup_textview_error_name)
        messages.add(view.auth_signup_textview_error_major)
        messages.add(view.auth_signup_textview_error_email)
        messages.add(view.auth_signup_textview_error_pw)
        messages.add(view.auth_signup_textview_error_pw_check)

        //마지막 패스워드 체크에서 확인 누를 시 바로 버튼 눌리게
        view.auth_edittext_signup_pw_check.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE) {
                view.auth_button_signin_from_signup.callOnClick()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setFocusChangeListener(view: View) {
        for(input in inputs) {
            input.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(c: Editable?) {
                    checkValidation(input, c.toString())
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            })
        }
    }

    private fun checkValidation(input : EditText, text : String) : Boolean{
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

    private fun checkEmail(input : EditText, message : TextView, email : String) : Boolean{
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

    private fun checkPassword(input : EditText, message : TextView, password : String) : Boolean{
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

    private fun checkPasswordCheck(input : EditText, message : TextView, password : String, pw_check : String) : Boolean {
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

    private inner class SignupClickListener(val view : View) : View.OnClickListener {
        var isGood = true
        override fun onClick(p0: View?) {
            resetInputs()
            validateInputs()

            if(isGood) {
                val id = view.auth_edittext_signup_email.text.toString()
                val name = view.auth_edittext_signup_name.text.toString()
                val major = view.auth_edittext_signup_major.text.toString()
                val password = view.auth_edittext_signup_pw.text.toString()
                val signupDto = SignUpDto(id, major, name, password)
                authActivity.requestSignup(signupDto)
                Log.d("Auth", "user input success")
            }
        }

        fun validateInputs() {
            for((i, input) in inputs.withIndex()) {
                val text = input.text.toString()
                if(!checkValidation(input, text))
                    isGood = false
            }
            checkBlankException()
        }

        fun checkBlankException() {
            for((i, input) in inputs.withIndex()) {
                if (input.text.isBlank()) {
                    isGood = false
                    val message = messages.get(i)
                    input.background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input_redline, null)
                    when(message.id) {
                        R.id.auth_signup_textview_error_name -> message.text = getString(R.string.auth_error_blank_signup_name)
                        R.id.auth_signup_textview_error_major -> message.text = getString(R.string.auth_error_blank_signup_major)
                        R.id.auth_signup_textview_error_email -> message.text = getString(R.string.auth_error_blank_signup_email)
                        R.id.auth_signup_textview_error_pw -> message.text = getString(R.string.auth_error_blank_signup_pw)
                        R.id.auth_signup_textview_error_pw_check -> message.text = getString(R.string.auth_error_blank_signup_pw_check)
                    }
                    message.visibility = View.VISIBLE
                }
            }
        }

        fun resetInputs() {
            isGood = true
            val strings = ArrayList<String>()
            strings.add(getString(R.string.auth_name))
            strings.add(getString(R.string.auth_major))
            strings.add(getString(R.string.auth_email))
            strings.add(getString(R.string.auth_pw))
            strings.add(getString(R.string.auth_pw_check))

            for(i in inputs.indices) {
                inputs.get(i).background = ResourcesCompat.getDrawable(resources, R.drawable.editview_auth_input, null)
                inputs.get(i).hint = strings.get(i)
                inputs.get(i).setHintTextColor(ResourcesCompat.getColor(resources, R.color.hint, null))
                inputs.get(i).setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
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
        authActivity.startCameraActivity()
    }
}