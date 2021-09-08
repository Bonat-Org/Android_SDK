package io.bonat.customer_lib.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Customer() : Parcelable {
    @SerializedName("idMerchant")
    @Expose
    val idMerchant: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("idCustomer")
    @Expose
    val idCustomer: String? = null

    @SerializedName("phoneNumber")
    @Expose
    val phoneNumber: String? = null

    @SerializedName("customerPoint")
    @Expose
    val customerPoint: String? = null

    @SerializedName("rewardsRedeemed")
    @Expose
    val rewardsRedeemed: String? = null

    @SerializedName("startDate")
    @Expose
    val startDate: String? = null

    @SerializedName("allowFeedback")
    @Expose
    val allowFeedback: Int? = null

    @SerializedName("blocked")
    @Expose
    val blocked: Int? = null

    @SerializedName("sdk_token")
    @Expose
    val token: String? = null


    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Customer> {
        override fun createFromParcel(parcel: Parcel): Customer {
            return Customer(parcel)
        }

        override fun newArray(size: Int): Array<Customer?> {
            return arrayOfNulls(size)
        }
    }
}