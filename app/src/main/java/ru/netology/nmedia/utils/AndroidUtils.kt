package ru.netology.nmedia.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object AndroidUtils {
    fun hideKeyboard(view: EditText){
       val inputMethodManagerneger =
           view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManagerneger.hideSoftInputFromWindow(view.windowToken,0)
    }
}