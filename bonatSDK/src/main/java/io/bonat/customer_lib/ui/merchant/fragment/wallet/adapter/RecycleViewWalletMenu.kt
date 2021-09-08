package io.bonat.customer_lib.ui.merchant.fragment.wallet.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.model.Coupon
import io.bonat.customer_lib.databinding.ListItemWalletViewBinding
import io.bonat.customer_lib.utils.ViewUtils
import java.util.*


class RecycleViewWalletMenu(private var context: Activity) :
    RecyclerView.Adapter<RecycleViewWalletMenu.RewardMenuHolder>() {
    private var listOfCoupon: ArrayList<Coupon>? = ArrayList()

    inner class RewardMenuHolder(var listItemWalletViewBinding: ListItemWalletViewBinding) :
        RecyclerView.ViewHolder(listItemWalletViewBinding.root) {
    }

    fun setData(dataList: ArrayList<Coupon>?) {
        this.listOfCoupon = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardMenuHolder {

        val listItemWalletViewBinding: ListItemWalletViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_wallet_view,
            parent,
            false
        )
        return RewardMenuHolder(listItemWalletViewBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RewardMenuHolder, position: Int) {
        holder.listItemWalletViewBinding.coupon = listOfCoupon!![position]
        holder.listItemWalletViewBinding.copyView.setOnClickListener {
            val clipboard: ClipboardManager? =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText(
                "Copy Text",
                holder.listItemWalletViewBinding.idCouponTv.text.toString()
            )
            clipboard!!.setPrimaryClip(clip)
            ViewUtils.toastMessage(context,context.getString(R.string.copy)+holder.listItemWalletViewBinding.idCouponTv.text.toString())
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return listOfCoupon!!.size
    }


}