package io.bonat.customer_lib.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class AlphaTextViewPressed : AppCompatTextView {
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
    }

    override fun setPressed(pressed: Boolean) {
        super.setPressed(pressed)
        alpha = if (pressed) 0.5f else 1.0f
    }
}