package com.example.orendatask.utils

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.example.orendatask.R

class ShowProgress {

    private var dialog:Dialog? = null

    fun show(context: Context) {
        dialog = Dialog(context , R.style.ProgressDialog)
        dialog?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        dialog?.setContentView(R.layout.alert_progress)
        dialog?.setCancelable(false)
        dialog?.show()
    }

    fun dismiss() {
        if (dialog != null) {
            if (dialog!!.isShowing) {
                dialog?.dismiss()
            }

        }
    }
}