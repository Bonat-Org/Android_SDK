package io.bonat.customer_lib.widget.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import io.bonat.customer_lib.R
import io.bonat.customer_lib.databinding.FeedbackViewBinding
import io.bonat.customer_lib.ui.merchant.MerchantActivity
import io.bonat.customer_lib.ui.merchant.fragment.MerchantView
import io.bonat.customer_lib.utils.Constant.ID_MERCHANT
import io.bonat.customer_lib.utils.Constant.MESSAGE
import io.bonat.customer_lib.utils.Constant.RATE
import io.bonat.customer_lib.utils.ViewUtils


class FeedbackDialog(var idMerchant: String,var merchantView: MerchantView) : DialogFragment() {


    private lateinit var binding: FeedbackViewBinding
    private lateinit var feedbackViewModel: FeedbackViewModel
    private var rateValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.PauseDialog)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(
            inflater, R.layout.feedback_view, container, false
        )
        binding.listener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observer()

    }

    private fun setupView() {
        feedbackViewModel =
            ViewModelProvider(this, (activity as MerchantActivity).viewModelFactory).get(
                FeedbackViewModel::class.java
            )
    }

    private fun observer() {
        feedbackViewModel.setFeedBackLiveData.observe(requireActivity(), {
            merchantView.successFeedBack()
            dismiss()
        })
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.feedback_parent_view -> dismiss()
            R.id.feedback_content_view -> {
            }
            R.id.skip -> {
                dismiss()
            }
            R.id.send_feedback -> {
                sendMessage()
            }
            R.id.ic_rate_1 -> {
                rateValue = 1
                ViewUtils.changeSelected(binding.emojiLayout,binding.icRate1)
            }
            R.id.ic_rate_2 -> {
                ViewUtils.changeSelected(binding.emojiLayout,binding.icRate2)
                rateValue = 2
            }
            R.id.ic_rate_3 -> {
                ViewUtils.changeSelected(binding.emojiLayout,binding.icRate3)
                rateValue = 3
            }
            R.id.ic_rate_4 -> {
                ViewUtils.changeSelected(binding.emojiLayout,binding.icRate4)
                rateValue = 4
            }
            R.id.ic_rate_5 -> {
                ViewUtils.changeSelected(binding.emojiLayout,binding.icRate5)
                rateValue = 5
            }
        }
    }

    private fun sendMessage() {
        if (rateValue == 0) {
            ViewUtils.toastMessage(requireActivity(), getString(R.string.select_imoji))
            return
        }
        val stringStringHashMap = HashMap<String, String>()
        stringStringHashMap[ID_MERCHANT] = idMerchant
        stringStringHashMap[MESSAGE] = binding.commentToManager.text.toString()
        stringStringHashMap[RATE] = rateValue.toString()

        feedbackViewModel.setFeedBack(activity, stringStringHashMap)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft: FragmentTransaction = manager.beginTransaction()
            ft.add(this, tag)
            ft.commit()
        } catch (e: IllegalStateException) {
        }
    }
}

