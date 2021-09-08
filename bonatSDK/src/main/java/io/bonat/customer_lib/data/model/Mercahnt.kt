package io.bonat.customer_lib.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Mercahnt() : Parcelable {
    @SerializedName("idMerchant")
    @Expose
    val idMerchant: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("name_ar")
    @Expose
    val name_ar: String? = null

    @SerializedName("customerPoint")
    @Expose
    val customerPoint: String? = null

    @SerializedName("totalPayments")
    @Expose
    val totalPayments: String? = null

    @SerializedName("rewardsRedeemed")
    @Expose
    val rewardsRedeemed: String? = null

    @SerializedName("email")
    @Expose
    val email: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("description_ar")
    @Expose
    val description_ar: String? = null

    @SerializedName("merchantImageUrl")
    @Expose
    val merchantImageUrl: String? = null

    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String? = null

    @SerializedName("link")
    @Expose
    val link: String? = null

    @SerializedName("phoneNumber")
    @Expose
    val phoneNumber: String? = null

    @SerializedName("lastVisit")
    @Expose
    val lastVisit: String? = null

    @SerializedName("workingHrStr")
    @Expose
    val workingHours: String? = null

    @SerializedName("isOpen")
    @Expose
    val isOpen: Boolean? = null

    @SerializedName("lat")
    @Expose
    val lat: String? = null

    @SerializedName("lng")
    @Expose
    val lng: String? = null

    @SerializedName("distance")
    @Expose
    val distance: String? = null

    @SerializedName("idBranch")
    @Expose
    val idBranch: String? = null

    @SerializedName("createdAt")
    @Expose
    val createdAt: String? = null

    @SerializedName("isLoyal")
    @Expose
    val isLoyal = false

    @SerializedName("idCity")
    @Expose
    val idCity = 0

    @SerializedName("district")
    @Expose
    val district: String? = null

    @SerializedName("allowFeedback")
    @Expose
    val allowFeedback = 0

    @SerializedName("baseVisit")
    @Expose
    val baseVisit = 0

    @SerializedName("totalVisits")
    @Expose
    val totalVisits = 0

    @SerializedName("idLoyaltyType")
    @Expose
    val idLoyaltyType = 0

    @SerializedName("RewardMenu")
    @Expose
    val rewardMenu: ArrayList<Campaign>? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mercahnt> {
        override fun createFromParcel(parcel: Parcel): Mercahnt {
            return Mercahnt(parcel)
        }

        override fun newArray(size: Int): Array<Mercahnt?> {
            return arrayOfNulls(size)
        }
    }
}