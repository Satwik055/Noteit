package com.satwik.noteit.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


object KeyboardUtil {

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm: InputMethodManager =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}