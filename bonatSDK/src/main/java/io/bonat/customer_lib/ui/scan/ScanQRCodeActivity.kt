package io.bonat.customer_lib.ui.scan

import android.Manifest
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.permissionx.guolindev.PermissionX
import io.bonat.customer_lib.LibApplication
import io.bonat.customer_lib.R
import io.bonat.customer_lib.databinding.ActivityScanQrcodeBinding
import io.bonat.customer_lib.ui.base.BaseActivity
import io.bonat.customer_lib.ui.merchant.MerchantActivity
import io.bonat.customer_lib.utils.Constant.DATA
import io.bonat.customer_lib.utils.Constant.ID_CUSTOMER
import io.bonat.customer_lib.utils.Constant.ID_ORDER
import io.bonat.customer_lib.utils.Constant.SDK
import io.bonat.customer_lib.utils.ViewUtils
import org.json.JSONObject
import java.util.*


class ScanQRCodeActivity : BaseActivity(), QRCodeView.Delegate {
    private lateinit var binding: ActivityScanQrcodeBinding
    private lateinit var scanQRCodeViewModel: ScanQRCodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = (application as LibApplication).component.activityComponentBuilder.build()
        component.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scan_qrcode)!!
        if (preferencesHelper!!.currentUser != null) {
            init()
            observe()
        }
    }


    private fun init() {
        scanQRCodeViewModel =
            ViewModelProvider(this, viewModelFactory).get(ScanQRCodeViewModel::class.java)
        binding.zbarview.setDelegate(this)

        PermissionX.init(this)
            .permissions(Manifest.permission.CAMERA)
            .request { allGranted, grantedList, deniedList ->

                binding.zbarview.startCamera()
                binding.zbarview.startSpotAndShowRect()
            }
        binding.icArrowBack.setOnClickListener {
            finish()
        }
        /* Dexter.withContext(this)
             .withPermission(Manifest.permission.CAMERA)
             .withListener(object : PermissionListener {
                 override fun onPermissionGranted(response: PermissionGrantedResponse) {
                     Log.e("withPermission", "onPermissionGranted")

                 }

                 override fun onPermissionDenied(response: PermissionDeniedResponse) {
                     Log.e("withPermission", "onPermissionDenied")
                 }

                 override fun onPermissionRationaleShouldBeShown(
                     permission: PermissionRequest?,
                     token: PermissionToken?
                 ) {
                     token!!.continuePermissionRequest()
                 }
             })*/
    }

    private fun observe() {
        scanQRCodeViewModel.scanQRCodeDataLiveData.observe(this, {
            binding.zbarview.startSpotAndShowRect()
            val intent = Intent(this, MerchantActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    override fun onScanQRCodeSuccess(result: String?) {
        vibrate()
        val dynamicLink = Uri.parse(result)
        val deepLinkData: JSONObject = ViewUtils.getJsonFromDeepLink(dynamicLink)
        val idOrder = deepLinkData.getString(ID_ORDER)
        val allParameter = HashMap<String, String>()
        allParameter[ID_ORDER] = idOrder
        allParameter[ID_CUSTOMER] = preferencesHelper!!.currentUser.idCustomer!!
        allParameter[DATA] = dynamicLink.getQueryParameter(DATA)!!
        allParameter[SDK] = "true"

        scanQRCodeViewModel.updateOrder(this, allParameter)

    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {
        var tipText: String = binding.zbarview.scanBoxView.tipText
        val ambientBrightnessTip = getString(R.string.enable_flash)
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                binding.zbarview.scanBoxView.tipText = tipText + ambientBrightnessTip
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip))
                binding.zbarview.getScanBoxView().setTipText(tipText)
            }
        }
    }

    override fun onScanQRCodeOpenCameraError() {
    }

    private fun vibrate() {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }

        val mediaPlayer: MediaPlayer = ViewUtils.playMusice(this, R.raw.tink)!!
        mediaPlayer.start()
    }

    override fun onStart() {
        super.onStart()
        binding.zbarview.startCamera()
        binding.zbarview.startSpotAndShowRect()
    }

    override fun onStop() {
        binding.zbarview.stopCamera()
        super.onStop()
    }

    override fun onDestroy() {
        binding.zbarview.onDestroy()
        super.onDestroy()
    }
}