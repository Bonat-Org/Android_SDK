package io.bonat.customer_lib.data.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.bonat.customer_lib.R
import io.bonat.customer_lib.utils.ViewUtils
import java.util.*

class UpdateOrder() : Parcelable {

    @SerializedName("message")
    @Expose
    var message: ArrayList<String>? = null

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<UpdateOrder> {
        override fun createFromParcel(parcel: Parcel): UpdateOrder {
            return UpdateOrder(parcel)
        }

        override fun newArray(size: Int): Array<UpdateOrder?> {
            return arrayOfNulls(size)
        }
    }


}