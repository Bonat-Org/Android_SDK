package io.bonat.customer_lib.data.model

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.bonat.customer_lib.R
import io.bonat.customer_lib.utils.ViewUtils
import java.util.*

class Campaign() : Parcelable {
    @SerializedName("idCampaign")
    @Expose
    val idCampaign: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("pointPrice")
    @Expose
    val pointPrice: String? = null

    @SerializedName("isLocked")
    @Expose
    val isLocked = false

    @SerializedName("images")
    @Expose
    val images: ArrayList<String>? = null

    constructor(parcel: Parcel) : this() {
    }


    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    /*    companion object CREATOR : Parcelable.Creator<Campaign> {
            override fun createFromParcel(parcel: Parcel): Campaign {
                return Campaign(parcel)
            }

            override fun newArray(size: Int): Array<Campaign?> {
                return arrayOfNulls(size)
            }
        }*/
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Campaign?> = object : Parcelable.Creator<Campaign?> {
            override fun createFromParcel(`in`: Parcel): Campaign {
                return Campaign(`in`)
            }

            override fun newArray(size: Int): Array<Campaign?> {
                return arrayOfNulls(size)
            }
        }
        @JvmStatic
        @BindingAdapter("LoadCampaignImage")
        fun setCampaignImageUrls(view: View, imageUrl: String?) {
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