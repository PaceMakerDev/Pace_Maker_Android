package com.example.ssutudy.util

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class DialogUtil {
    companion object {
        fun showAlertDialog(context : Context, title : String, msg : String) {
            AlertDialog.Builder(context).apply {
                setTitle(title)
                setMessage(msg)
                setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i -> })
                show()
            }
        }
    }
}