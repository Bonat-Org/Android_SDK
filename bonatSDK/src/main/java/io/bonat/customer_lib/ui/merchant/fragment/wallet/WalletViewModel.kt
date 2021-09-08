package io.bonat.customer_lib.ui.merchant.fragment.wallet

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.app.easytable.ui.base.BaseViewModel
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.BaseResponseObject
import io.bonat.customer_lib.data.DataManager
import io.bonat.customer_lib.data.model.Wallet
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WalletViewModel(private val dataManager: DataManager) : BaseViewModel() {
    var walletDataLiveData = MutableLiveData<BaseResponseObject<Wallet>>()

    fun showWalletDataData(activity: Activity?) { // lat and lng
        showProgress(false, activity!!)
        dataManager.walletData
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<BaseResponseObject<Wallet>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(response: BaseResponseObject<Wallet>) {
                    showProgress(false, activity)

                    if (response.code == 0) {
                        walletDataLiveData.postValue(response)
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