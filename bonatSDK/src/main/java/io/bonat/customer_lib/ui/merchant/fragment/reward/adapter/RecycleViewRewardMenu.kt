package io.bonat.customer_lib.ui.merchant.fragment.reward.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.model.Campaign
import io.bonat.customer_lib.databinding.ListItemRewardViewBinding
import io.bonat.customer_lib.ui.merchant.fragment.reward.RewardView
import java.util.ArrayList

class RecycleViewRewardMenu(private var context: Activity, private var rewardView: RewardView) :
    RecyclerView.Adapter<RecycleViewRewardMenu.RewardMenuHolder>() {

    private var listOfCampaign: ArrayList<Campaign>? = ArrayList()

    inner class RewardMenuHolder(var listItemRewardViewBinding: ListItemRewardViewBinding) :
        RecyclerView.ViewHolder(listItemRewardViewBinding.root) {
    }

    fun setData(dataList: ArrayList<Campaign>?) {
        this.listOfCampaign = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardMenuHolder {

        val listItemRewardViewBinding: ListItemRewardViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_reward_view,
            parent,
            false
        )
        return RewardMenuHolder(listItemRewardViewBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RewardMenuHolder, position: Int) {
        holder.listItemRewardViewBinding.campaign = listOfCampaign!![position]
        holder.listItemRewardViewBinding.collectBt.setOnClickListener {
          //  if (!listOfCampaign!![position].isLocked)
                rewardView.collectReward(listOfCampaign!![position].idCampaign.toString())
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return listOfCampaign!!.size
    }


}