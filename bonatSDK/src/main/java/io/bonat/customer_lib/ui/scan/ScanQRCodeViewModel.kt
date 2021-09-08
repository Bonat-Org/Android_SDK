package io.bonat.customer_lib.ui.scan

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.easytable.ui.base.BaseViewModel
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.BaseResponseArray
import io.bonat.customer_lib.data.BaseResponseObject
import io.bonat.customer_lib.data.DataManager
import io.bonat.customer_lib.data.model.UpdateOrder
import io.bonat.customer_lib.utils.Constant
import io.bonat.customer_lib.utils.ViewUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ScanQRCodeViewModel(private val dataManager: DataManager) : BaseViewModel() {
    var scanQRCodeDataLiveData = MutableLiveData<BaseResponseArray<String>>()

    fun updateOrder(activity: Activity?, data: HashMap<String, String>) { // lat and lng
        showProgress(true, activity!!)
        dataManager.updateOrder(Constant.URL_FIREBASE, data)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<BaseResponseArray<String>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(response: BaseResponseArray<String>) {
                    showProgress(false, activity)
                    Log.e("onSubscribe","response - "+response.data!!.size)
                    if (response.code == 0) {
                        scanQRCodeDataLiveData.postValue(response)
                        if (response.data != null && response.data!!.size > 0
                        ) {
                            ViewUtils.showSuccessAlert(activity, response.data!![0])
                        }
                    }

                }

                override fun onError(e: Throwable) {
                    showAlert(
                        activity,
                        e.message!!,
                        R.drawable.ic_baseline_cancel_24
                    )
                    showProgress(false, activity)
                }

                override fun onComplete() {
                    showProgress(false, activity)
                }
            })
    }
}