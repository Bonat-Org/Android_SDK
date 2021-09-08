package io.bonat.customer_lib.widget.dialog

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.easytable.ui.base.BaseViewModel
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.BaseResponse
import io.bonat.customer_lib.data.BaseResponseObject
import io.bonat.customer_lib.data.DataManager
import io.bonat.customer_lib.data.model.Mercahnt
import io.bonat.customer_lib.data.model.Wallet
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.HashMap

class FeedbackViewModel(private val dataManager: DataManager) : BaseViewModel() {
    var setFeedBackLiveData = MutableLiveData<BaseResponse>()

    fun setFeedBack(activity: Activity?, hash: HashMap<String, String>) { // lat and lng
        showProgress(true, activity!!)
        dataManager.setFeedBack(hash)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<BaseResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(response: BaseResponse ) {
                    showProgress(false, activity)
                    if (response.code == 0) {
                        setFeedBackLiveData.postValue(response)
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