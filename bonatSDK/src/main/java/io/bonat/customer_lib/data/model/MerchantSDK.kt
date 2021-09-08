package io.bonat.customer_lib.data.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MerchantSDK() : Parcelable {

    @SerializedName("idMerchant")
    @Expose
    var idMerchant: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("sdk_token")
    @Expose
    var sdk_token: String? = null

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<MerchantSDK> {
        override fun createFromParcel(parcel: Parcel): MerchantSDK {
            return MerchantSDK(parcel)
        }

        override fun newArray(size: Int): Array<MerchantSDK?> {
            return arrayOfNulls(size)
        }
    }


}