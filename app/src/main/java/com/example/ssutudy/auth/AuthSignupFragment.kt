package com.example.ssutudy.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.ssutudy.R
import com.example.ssutudy.auth.models.SignUpDto
import com.example.ssutudy.util.AuthValidator
import kotlinx.android.synthetic.main.fragment_auth_login.view.*
import kotlinx.android.synthetic.main.fragment_auth_signup.*
import kotlinx.android.synthetic.main.fragment_auth_signup.view.*
import kotlinx.android.synthetic.main.fragment_auth_signup.view.auth_signup_textview_error_email

class AuthSignupFragment : Fragment() {

    lateinit private var authActivity: AuthActivity
    lateinit private var rootView : View
    private var inputs : ArrayList<EditText> = arrayListOf()
    private var messages : ArrayList<TextView> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView= inflater.inflate(R.layout.fragment_auth_signup, container, false)
        initialSetUp()
        setFocusChangeListener()
        rootView.auth_button_signup_with_input.setOnClickListener(SignupClickListener())


        rootView.auth_button_student_card.setOnClickListener {
            //카메라 인식
        }


        return return rootView
    }

    fun initialSetUp() {
        //edittext들 arraylist에 넣어주기
        inputs.add(rootView.auth_edittext_signup_name)
        inputs.add(rootView.auth_edittext_signup_major)
        inputs.add(rootView.auth_edittext_signup_email)
        inputs.add(rootView.auth_edittext_signup_pw)
        inputs.add(rootView.auth_edittext_signup_pw_check)

        //edittext들 arrayList에 넣기
        messages.add(rootView.auth_signup_textview_error_name)
        messages.add(rootView.auth_signup_textview_error_major)
        messages.add(rootView.auth_signup_textview_error_email)
        messages.add(rootView.auth_signup_textview_error_pw)
        messages.add(rootView.auth_signup_textview_error_pw_check)

        //마지막 패스워드 체크에서 확인 누를 시 바로 버튼 눌리게
        rootView.auth_edittext_signup_pw_check.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE) {
                rootView.auth_button_signup_with_input.callOnClick()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setFocusChangeListener() {
        for(input in inputs) {
            input.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(c: Editable?) {
                    //AuthValidator.checkValidation(input, c.toString())
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            })
        }
    }

    private inner class SignupClickListener() : View.OnClickListener {
        var isGood = true
        override fun onClick(p0: View?) {
            resetInputs()
            validateInputs()

            if(isGood) {
                val email = rootView.auth_edittext_signup_email.text.toString()
                val name = rootView.auth_edittext_signup_name.text.toString()
                val major = rootView.auth_edittext_signup_major.text.toString()
                val password = authActivity.encryptSHA256(rootView.auth_edittext_signup_pw.text.toString())
                val signupDto = SignUpDto(email, major, name, password)
                authActivity.requestSignup(signupDto)
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
                inputs.get(i).setHintTextColor(ResourcesCompat.getColor(resources, R.color.gray, null))
                inputs.get(i).setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
            }
            for(message in messages) {
                message.text = ""
                message.visibility = View.GONE
            }
        }

        fun validateInputs() {
            for((i, input) in inputs.withIndex()) {
                val text = input.text.toString()
                /*
                if(!AuthValidator.checkValidation(input, text))
                    isGood = false
                    
                 */
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


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authActivity = activity as AuthActivity
        authActivity.startCameraActivity()
    }
}