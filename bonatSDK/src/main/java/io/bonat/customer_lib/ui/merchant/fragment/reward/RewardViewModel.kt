package io.bonat.customer_lib.ui.merchant.fragment.reward

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.app.easytable.ui.base.BaseViewModel
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.BaseResponseObject
import io.bonat.customer_lib.data.DataManager
import io.bonat.customer_lib.data.model.Coupon
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RewardViewModel(private val dataManager: DataManager) : BaseViewModel() {
    var collectRewardDataLiveData = MutableLiveData<BaseResponseObject<Coupon>>()

    fun showWalletDataData(activity: Activity?, id:String) { // lat and lng
        showProgress(true, activity!!)
        dataManager.pointRedemption(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<BaseResponseObject<Coupon>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(response: BaseResponseObject<Coupon>) {
                    showProgress(false, activity)
                    if (response.code == 0) {
                        collectRewardDataLiveData.postValue(response)
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