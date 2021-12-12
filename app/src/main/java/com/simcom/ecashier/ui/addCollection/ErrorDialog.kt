package com.simcom.ecashier.ui.addCollection

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.simcom.ecashier.R

class ErrorDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_error)
        findViewById<View>(R.id.button).setOnClickListener { dismiss() }
    }
}