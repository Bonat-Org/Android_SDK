package io.bonat.customer_lib.ui.merchant.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerReward (manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val fragmentList : MutableList<androidx.fragment.app.Fragment> = ArrayList()
    private val titleList : MutableList<String> = ArrayList()

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: androidx.fragment.app.Fragment, title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }
}