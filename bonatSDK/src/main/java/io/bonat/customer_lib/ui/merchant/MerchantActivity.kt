package io.bonat.customer_lib.ui.merchant

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import io.bonat.customer_lib.LibApplication
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.model.EventBusClass
import io.bonat.customer_lib.data.model.Mercahnt
import io.bonat.customer_lib.databinding.ActivityMerchantBinding
 import io.bonat.customer_lib.ui.base.BaseActivity
import io.bonat.customer_lib.ui.merchant.adapter.ViewPagerReward
import io.bonat.customer_lib.ui.merchant.fragment.MerchantView
import io.bonat.customer_lib.ui.merchant.fragment.reward.RewardFragment
import io.bonat.customer_lib.ui.merchant.fragment.wallet.WalletFragment
import io.bonat.customer_lib.utils.Constant
import io.bonat.customer_lib.utils.Constant.REWARD_MENU
import io.bonat.customer_lib.widget.dialog.FeedbackDialog
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MerchantActivity : BaseActivity(), MerchantView {
    private var merchantData: Mercahnt? = null
    private lateinit var merchantViewModel: MerchantViewModel
    private lateinit var binding: ActivityMerchantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = (application as LibApplication).component.activityComponentBuilder.build()
        component.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_merchant)!!
        binding.listener = this
        init()
        getData()
        observe()

    }


    private fun init() {
        merchantViewModel =
            ViewModelProvider(this, viewModelFactory).get(MerchantViewModel::class.java)
        binding.refreshLayout.isNestedScrollingEnabled = false
        val viewPagerReward = ViewPagerReward(supportFragmentManager)
        viewPagerReward.addFragment(RewardFragment(), getString(R.string.rewards))
        viewPagerReward.addFragment(WalletFragment(), getString(R.string.wallet))
        binding.viewPager.adapter = viewPagerReward

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.tabLayout.getTabAt(0)!!.select()
                } else {
                    binding.tabLayout.getTabAt(1)!!.select()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0) {
                    binding.viewPager.currentItem = 0
                } else {
                    binding.viewPager.currentItem = 1
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.refreshLayout.setOnRefreshListener {
            merchantViewModel.showMerchantData(this, preferencesHelper!!.currentUser.idMerchant)

        }
        EventBus.getDefault().register(this)
    }

    private fun getData() {

        binding.refreshLayout.isRefreshing = true
        merchantViewModel.showMerchantData(this, preferencesHelper!!.currentUser.idMerchant)

    }

    private fun observe() {
        merchantViewModel.merchantDataLiveData.observe(this, {
            merchantData = it.data
            binding.mercahnt = it.data

            EventBus.getDefault()
                .post(EventBusClass(Gson().toJson(it.data.rewardMenu), REWARD_MENU))

            binding.refreshLayout.isRefreshing = false
            binding.contentView.visibility = View.VISIBLE
        })
    }

    fun onClickState(view: View) {
        when (view.id) {
            R.id.ic_rate_1, R.id.ic_rate_2, R.id.ic_rate_3, R.id.ic_rate_4, R.id.ic_rate_5 -> {
                FeedbackDialog(merchantData!!.idMerchant!!, this).show(
                    supportFragmentManager,
                    "FeedbackDialog"
                )

            }
            R.id.ic_arrow_back_->{
                finish()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventBusClass?) {
        if (event!!.type != null && event.type.equals(Constant.COLLECT_REWARD)) {
            getData()
            binding.tabLayout.getTabAt(1)!!.select()
        }
    }

    override fun successFeedBack() {
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)

    }
}