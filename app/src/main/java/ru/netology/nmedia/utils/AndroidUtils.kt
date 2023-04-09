package ru.netology.nmedia.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object AndroidUtils {
    fun hideKeyboard(view:View){
       val inputMethodMeneger =
           view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodMeneger.hideSoftInputFromWindow(view.windowToken,0)
    }
}