package io.bonat.customer_lib.ui.merchant.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.bonat.customer_lib.ui.merchant.fragment.reward.RewardFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return RewardFragment.newInstance()
    }

    override fun getItemCount(): Int {
        return CARD_ITEM_SIZE
    }

    companion object {
        private const val CARD_ITEM_SIZE = 2
    }
}