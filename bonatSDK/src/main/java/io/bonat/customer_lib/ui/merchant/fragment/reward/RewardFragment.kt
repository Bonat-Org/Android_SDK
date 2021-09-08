package io.bonat.customer_lib.ui.merchant.fragment.reward

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.model.Campaign
import io.bonat.customer_lib.data.model.EventBusClass
import io.bonat.customer_lib.databinding.FragmentRewardBinding
import io.bonat.customer_lib.ui.merchant.MerchantActivity
import io.bonat.customer_lib.ui.merchant.fragment.reward.adapter.RecycleViewRewardMenu
import io.bonat.customer_lib.utils.Constant.COLLECT_REWARD
import io.bonat.customer_lib.utils.Constant.REWARD_MENU
import io.bonat.customer_lib.utils.ViewUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.ArrayList


class RewardFragment : Fragment(),RewardView {
    private lateinit var rewardViewModel: RewardViewModel
    private lateinit var recycleViewRewardMenu: RecycleViewRewardMenu
    lateinit var binding: FragmentRewardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_reward, container, false
        )
        //binding.home = this

        return binding.root
    }

    companion object {
        fun newInstance(): RewardFragment {
            val fragment = RewardFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inti()
    }

    private fun inti() {
        rewardViewModel =
            ViewModelProvider(this, (activity as MerchantActivity).viewModelFactory).get(
                RewardViewModel::class.java
            )

        recycleViewRewardMenu = RecycleViewRewardMenu(requireActivity(),this)
        binding.rewardList.adapter = recycleViewRewardMenu

        rewardViewModel.collectRewardDataLiveData.observe(requireActivity(),{
            EventBus.getDefault()
                .post(EventBusClass(Gson().toJson(it.data), COLLECT_REWARD))
            ViewUtils.toastMessage(requireContext(),getString(R.string.reward_success_collect))


        })
        EventBus.getDefault().register(this)

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventBusClass?) {
        Log.e("onMessageEvent","onMessageEvent");
        if (event!!.type != null && event.type.equals(REWARD_MENU)) {
            val rewardListType = object : TypeToken<ArrayList<Campaign>>() {}.type
            val list = Gson().fromJson<ArrayList<Campaign>>(event.data.toString(), rewardListType)
            if (list.size==0){
                binding.noDataRewardView.visibility=View.VISIBLE
            }else{
                binding.noDataRewardView.visibility=View.GONE

            }
          recycleViewRewardMenu.setData(list)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)

    }

    override fun collectReward(id: String) {
        rewardViewModel.showWalletDataData(requireActivity(),id)
    }
}