package io.bonat.customer_lib.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.easytable.ui.base.ViewModelFactory
import io.bonat.customer_lib.LibApplication
import io.bonat.customer_lib.data.local.PreferencesHelper
import io.bonat.customer_lib.utils.LocaleHelper

import java.util.*
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @JvmField
    @Inject
    var preferencesHelper: PreferencesHelper? = null

    @JvmField
    @Inject
    var localeHelper: LocaleHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val component = (application as LibApplication).component.activityComponentBuilder.build()
        component.inject(this)

        super.onCreate(savedInstanceState)
//       / ToolUtils.changeLanguage(this,preferencesHelper!!.getStringWithKey(LANGUAGE, Locale.getDefault().language)!!)
    }



    fun reCreateBase() {
        recreate()
    }
}