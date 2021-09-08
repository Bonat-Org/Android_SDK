package io.bonat.customer_lib.ui.merchant

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.app.easytable.ui.base.BaseViewModel
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.BaseResponseObject
import io.bonat.customer_lib.data.DataManager
import io.bonat.customer_lib.data.model.Mercahnt
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MerchantViewModel(private val dataManager: DataManager) : BaseViewModel() {
    var merchantDataLiveData = MutableLiveData<BaseResponseObject<Mercahnt>>()

    fun showMerchantData(activity: Activity?, id: String?) { // lat and lng
        showProgress(false, activity!!)
        dataManager.showMerchantData(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<BaseResponseObject<Mercahnt>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(response: BaseResponseObject<Mercahnt>) {
                    showProgress(false, activity)
                  //  Log.e("rewardMenu",""+it.data.rewardMenu!!.size)

                    if (response.code == 0) {
                        merchantDataLiveData.postValue(response)
                    }
                    /* if (userDataBaseResponse.status == 1) {
                         favoriteMutableLiveData.postValue(userDataBaseResponse)
                     }
                     if (userDataBaseResponse.opereation == DELETE) {
                         showAlert(
                             activity,
                             userDataBaseResponse.message,
                             R.drawable.ic_baseline_cancel_24
                         )
                     } else {
                         showSuccessAlert(activity, userDataBaseResponse.message)
                     }*/
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