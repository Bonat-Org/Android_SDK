package io.bonat.customer_lib.data.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Wallet() : Parcelable {
    @SerializedName("History")
    @Expose
    var history: ArrayList<Coupon>? = null

    @SerializedName("Coupons")
    @Expose
    var coupons: ArrayList<Coupon>?= null

    @SerializedName("Gifted")
    @Expose
    var gifted: ArrayList<Coupon>?= null

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Wallet> {
        override fun createFromParcel(parcel: Parcel): Wallet {
            return Wallet(parcel)
        }

        override fun newArray(size: Int): Array<Wallet?> {
            return arrayOfNulls(size)
        }
    }

}