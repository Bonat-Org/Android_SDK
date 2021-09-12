package io.bonat.customer_lib

import android.app.Application
import android.util.Log
import com.koushikdutta.ion.Ion
import io.bonat.customer_lib.data.model.Mode
import io.bonat.customer_lib.di.component.ApplicationComponent
import io.bonat.customer_lib.di.component.DaggerApplicationComponent
import io.bonat.customer_lib.di.module.ContextModule
import io.bonat.customer_lib.di.module.RetrofitModule
import io.bonat.customer_lib.utils.Bonat
import io.bonat.customer_lib.utils.Constant.REQUEST_URL
import io.bonat.customer_lib.utils.Constant.URL
import javax.crypto.SecretKey


open class BonatApplication : Application() {
    lateinit var component: ApplicationComponent
    override fun onCreate() {
        super.onCreate()

    }

    fun initBonatSDK(merchantID: String, secretKey: String, mode:Mode) {
        Bonat.initializeSDK(this, merchantID, secretKey, mode)
        component = DaggerApplicationComponent.builder().contextModule(ContextModule(this))
            .retrofitModule(RetrofitModule(this, REQUEST_URL)).build()
        Ion.getDefault(this).conscryptMiddleware.enable(false)
    }
}