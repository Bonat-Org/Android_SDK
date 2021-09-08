package io.bonat.customer_lib.ui.merchant.fragment.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.model.EventBusClass
import io.bonat.customer_lib.databinding.FragmentWalletBinding
import io.bonat.customer_lib.ui.merchant.MerchantActivity
import io.bonat.customer_lib.ui.merchant.fragment.wallet.adapter.RecycleViewWalletMenu
import io.bonat.customer_lib.utils.Constant
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class WalletFragment : Fragment() {
    private lateinit var recycleViewWalletMenu: RecycleViewWalletMenu
    private lateinit var walletViewModel: WalletViewModel
    lateinit var binding: FragmentWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_wallet, container, false
        )
        //binding.home = this

        return binding.root
    }

    companion object {
        fun newInstance(): WalletFragment {
            val fragment = WalletFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inti()
        observe()
    }


    private fun inti() {
        walletViewModel =
            ViewModelProvider(this, (activity as MerchantActivity).viewModelFactory).get(
                WalletViewModel::class.java
            )

        binding.refreshLayout.isNestedScrollingEnabled = false
        recycleViewWalletMenu = RecycleViewWalletMenu(requireActivity())
        binding.walletList.adapter = recycleViewWalletMenu
        binding.refreshLayout.isRefreshing = true
        binding.refreshLayout.setOnRefreshListener {
            getData()
        }
        getData()
        EventBus.getDefault().register(this)

    }

    private fun observe() {
        walletViewModel.walletDataLiveData.observe(requireActivity(), {
            if (it.data.coupons!!.size==0){
                binding.noDataWalletView.visibility=View.VISIBLE
            }else{
                binding.noDataWalletView.visibility=View.GONE

            }
            recycleViewWalletMenu.setData(it.data.coupons)
            binding.refreshLayout.isRefreshing = false
        })
    }

    private fun getData() {
        walletViewModel.showWalletDataData(requireActivity())
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventBusClass?) {
        if (event!!.type != null && event.type.equals(Constant.COLLECT_REWARD)) {
            getData()
        }


    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)

    }
}