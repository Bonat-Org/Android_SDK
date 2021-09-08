package com.app.easytable.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.bonat.customer_lib.data.DataManager
import io.bonat.customer_lib.ui.merchant.MerchantViewModel
import io.bonat.customer_lib.ui.merchant.fragment.reward.RewardViewModel
import io.bonat.customer_lib.ui.merchant.fragment.wallet.WalletViewModel
import io.bonat.customer_lib.ui.scan.ScanQRCodeViewModel
import io.bonat.customer_lib.widget.dialog.FeedbackViewModel

import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    @JvmField
    @Inject
    var dataManager: DataManager? = null
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {

            modelClass.isAssignableFrom(MerchantViewModel::class.java) -> {
                return MerchantViewModel(dataManager!!) as T
            }
            modelClass.isAssignableFrom(WalletViewModel::class.java) -> {
                return WalletViewModel(dataManager!!) as T
            }
            modelClass.isAssignableFrom(FeedbackViewModel::class.java) -> {
                return FeedbackViewModel(dataManager!!) as T
            }
            modelClass.isAssignableFrom(ScanQRCodeViewModel::class.java) -> {
                return ScanQRCodeViewModel(dataManager!!) as T
            }
            modelClass.isAssignableFrom(RewardViewModel::class.java) -> {
                return RewardViewModel(dataManager!!) as T
            }

            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}