package com.example.mathquiz.dialogs

import android.app.AlertDialog
import android.content.Context

open class BaseDialog(context: Context?) : AlertDialog(context)

interface DialogInteraction {
    fun onFirstOptionClicked() {}
    fun onSecondOptionClicked() {}
}