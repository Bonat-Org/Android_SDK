package com.app.easytable.ui.base

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import io.bonat.customer_lib.utils.ViewUtils

import java.util.*

open class BaseViewModel( ) : ViewModel() {
    private var progress: KProgressHUD? = null


    fun showProgress(b: Boolean, activity: Activity) {
        if (progress == null) {
            progress = ViewUtils.progressBar(activity, "Please wait", true)
        }
        if (b) {
            progress!!.show()
        } else {
            progress!!.dismiss()
        }
    }

    fun showAlert(activity: Activity, message: String, icon: Int) {
        ViewUtils.showAlert(
            activity,
            message,
            icon
        )

    }

}