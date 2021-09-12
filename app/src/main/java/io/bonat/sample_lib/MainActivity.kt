package io.bonat.sample_lib

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.bonat.customer_lib.StartView
import io.bonat.customer_lib.data.local.PreferencesHelper
import io.bonat.customer_lib.ui.merchant.MerchantActivity
import io.bonat.customer_lib.ui.scan.ScanQRCodeActivity
import io.bonat.customer_lib.utils.Bonat
import io.bonat.customer_lib.utils.Constant.SDK_TOKEN
import io.bonat.customer_lib.utils.ViewUtils.Companion.disableView
import io.bonat.sample_lib.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), StartView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferencesHelper: PreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)!!
        preferencesHelper = PreferencesHelper(this)

        disableView(binding.actionView, false)
        binding.startBt.setOnClickListener {
            if ((preferencesHelper.getStringWithKey(
                    SDK_TOKEN,
                    null
                )) != null && binding.customerPhoneEt.text.toString() != ""
            ) {
                Bonat.getCustomerInfo(this, binding.customerPhoneEt.text.toString(), this)
            }
        }
        binding.detailsBt.setOnClickListener {
            val intent = Intent(this@MainActivity, MerchantActivity::class.java)
            startActivity(intent)
        }
        binding.scanBt.setOnClickListener {
            val intent = Intent(this@MainActivity, ScanQRCodeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun finishSDK() {
        disableView(binding.actionView, true)

    }


}