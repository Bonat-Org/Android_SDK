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

class Coupon : Parcelable {
    @SerializedName("idCoupon")
    @Expose
    var idCoupon: String? = null

    @SerializedName("idCustomer")
    @Expose
    var idCustomer: String? = null

    @SerializedName("idCampaign")
    @Expose
    var idCampaign: String? = null

    @SerializedName("campaignType")
    @Expose
    var campaignType: String? = null

    @SerializedName("idCampaignType")
    @Expose
    var idCampaignType = 0

    @SerializedName("idBranch")
    @Expose
    var idBranch: String? = null

    @SerializedName("idProduct")
    @Expose
    var idProduct: String? = null

    @SerializedName("posIdBranch")
    @Expose
    var posIdBranch: String? = null

    @SerializedName("posIdProduct")
    @Expose
    var posIdProduct: String? = null

    @SerializedName("posBusinessId")
    @Expose
    var posBusinessId: String? = null

    @SerializedName("usedDate")
    @Expose
    var usedDate: String? = null

    @SerializedName("expirationDate")
    @Expose
    var expirationDate: String? = null

    @SerializedName("giftedDate")
    @Expose
    var giftedDate: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("idMerchant")
    @Expose
    var idMerchant: String? = null

    @SerializedName("quantityLimit")
    @Expose
    var quantityLimit = 0

    @SerializedName("isValid")
    @Expose
    var isValid = 0

    @SerializedName("isUsed")
    @Expose
    var isUsed = 0

    @SerializedName("isReward")
    @Expose
    var isReward = 0

    @SerializedName("oldPrice")
    @Expose
    var oldPrice = 0.0

    @SerializedName("newPrice")
    @Expose
    var newPrice = 0.0

    @SerializedName("price")
    @Expose
    var priceWithVat = 0.0

    @SerializedName("VAT")
    @Expose
    var vat: String? = null

    @SerializedName("numAvailable")
    @Expose
    var numAvailable = 0

    @SerializedName("merchantImageUrl")
    @Expose
    var merchantImageUrl: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("lifeTime")
    @Expose
    var lifeTime: Int? = null

    @SerializedName("from")
    @Expose
    var from: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("prevIdCustomer")
    @Expose
    var prevIdCustomer: String? = null

    @SerializedName("sentTo")
    @Expose
    var sentTo: String? = null

    @SerializedName("imageUrl")
    @Expose
    var imageUrl: ArrayList<String>? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    constructor() {}
    constructor(idCoupon: String?) {
        this.idCoupon = idCoupon
    }

    constructor(idCoupon: String?, idCampaignType: Int, merchantImageUrl: String?) {
        this.idCoupon = idCoupon
        this.idCampaignType = idCampaignType
        this.merchantImageUrl = merchantImageUrl
    }

    protected constructor(`in`: Parcel) {
        idCoupon = `in`.readString()
        idCustomer = `in`.readString()
        idCampaign = `in`.readString()
        campaignType = `in`.readString()
        idCampaignType = `in`.readInt()
        idBranch = `in`.readString()
        idProduct = `in`.readString()
        posIdBranch = `in`.readString()
        posIdProduct = `in`.readString()
        posBusinessId = `in`.readString()
        usedDate = `in`.readString()
        expirationDate = `in`.readString()
        giftedDate = `in`.readString()
        title = `in`.readString()
        description = `in`.readString()
        idMerchant = `in`.readString()
        quantityLimit = `in`.readInt()
        lifeTime = `in`.readInt()
        isValid = `in`.readInt()
        isUsed = `in`.readInt()
        isReward = `in`.readInt()
        oldPrice = `in`.readDouble()
        newPrice = `in`.readDouble()
        priceWithVat = `in`.readDouble()
        numAvailable = `in`.readInt()
        merchantImageUrl = `in`.readString()
        vat = `in`.readString()
        name = `in`.readString()
        from = `in`.readString()
        message = `in`.readString()
        prevIdCustomer = `in`.readString()
        sentTo = `in`.readString()
        icon = `in`.readString()
        imageUrl = `in`.createStringArrayList()
    }

    override fun equals(obj: Any?): Boolean {
        //return super.equals(obj);
        val other = obj as Coupon?
        return idCoupon == other!!.idCoupon
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(idCoupon)
        dest.writeString(idCustomer)
        dest.writeString(idCampaign)
        dest.writeString(campaignType)
        dest.writeInt(idCampaignType)
        dest.writeString(idBranch)
        dest.writeString(idProduct)
        dest.writeString(posIdBranch)
        dest.writeString(posIdProduct)
        dest.writeString(posBusinessId)
        dest.writeString(usedDate)
        dest.writeString(expirationDate)
        dest.writeString(giftedDate)
        dest.writeString(title)
        dest.writeString(description)
        dest.writeString(idMerchant)
        dest.writeInt(quantityLimit)
        dest.writeInt(isValid)
        dest.writeInt(isUsed)
        dest.writeInt(isReward)
        dest.writeDouble(oldPrice)
        dest.writeDouble(newPrice)
        dest.writeDouble(priceWithVat)
        dest.writeInt(numAvailable)
        dest.writeInt(lifeTime!!)
        dest.writeString(merchantImageUrl)
        dest.writeString(vat)
        dest.writeString(name)
        dest.writeString(from)
        dest.writeString(message)
        dest.writeString(prevIdCustomer)
        dest.writeString(sentTo)
        dest.writeString(icon)
        dest.writeStringList(imageUrl)
    }

    companion object {
        @JvmField val CREATOR: Creator<Coupon?> = object : Creator<Coupon?> {
            override fun createFromParcel(`in`: Parcel): Coupon {
                return Coupon(`in`)
            }

            override fun newArray(size: Int): Array<Coupon?> {
                return arrayOfNulls(size)
            }
        }

        @JvmStatic
        @BindingAdapter("LoadImageCoupon")
        fun setCouponImageUrls(view: View, imageUrl: String?) {
            val imageView = view as ImageView
            ViewUtils.glideLoadImage(
                imageView.context,
                imageUrl,
                R.drawable.default_image_large,
                imageView,
                null
            )
        }
    }
}