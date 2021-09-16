package io.bonat.customer_lib.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.util.Base64
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson
import com.kaopiz.kprogresshud.KProgressHUD
import com.tapadoo.alerter.Alerter
import io.bonat.customer_lib.R
import io.bonat.customer_lib.data.model.ErrorResponse
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*


class ViewUtils {
    companion object {

        @JvmStatic
        fun disableView(viewAction: ViewGroup, isEnable: Boolean) {
            for (i in 0 until viewAction.childCount) {
                val view = viewAction.getChildAt(i)
                view.isEnabled = isEnable // Or whatever you want to do with the view.
            }
        }

        @JvmStatic
        fun progressBar(
            @NonNull mContext: Context?,
            title: String?,
            cancellable: Boolean
        ): KProgressHUD? {
            return KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(title)
                .setCancellable(cancellable)
                .setBackgroundColor(ContextCompat.getColor(mContext!!, R.color.color_7D7B78))
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
        }

        fun showAlert(mContext: Activity?, message: String?, icon: Int) {
            Alerter.create(mContext!!)
                .setText(message!!)
                .setIcon(icon)
                .setIconColorFilter(0)
                .enableSwipeToDismiss()
                .setBackgroundColorRes(R.color.color_7D7B78)
                .show()
        }

        @JvmStatic
        fun showSuccessAlert(mContext: Activity?, message: String?) {
            Alerter.create(mContext!!)
                .setText(message!!)
                .setIcon(R.drawable.ic_success)
                .setIconColorFilter(0)
                .enableSwipeToDismiss()
                .setBackgroundColorRes(R.color.color_22bb33)
                .show()
        }

        fun glideLoadImage(
            context: Context?,
            url: String?,
            placeholder: Int,
            imageView: ImageView?,
            progressBar: ProgressBar?
        ) {
            if (context == null) {
                return
            }
            Glide.with(context)
                .load(url)
                .placeholder(placeholder)
                .addListener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (progressBar != null) {
                            progressBar.visibility = View.GONE
                        }
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: Target<Drawable?>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (progressBar != null) {
                            progressBar.visibility = View.GONE
                        }
                        return false
                    }
                })
                .into(imageView!!)
        }

        @JvmStatic
        fun convertDateFromFormatToFormat(
            date: String?,
            format1: String?,
            format2: String?
        ): String? { //"yyyy-MM-dd'T'HH:mm:ssZ" Constant.EEEE_dd_MMM_yyyy
            return convertDateToString(
                convertStringToDate(
                    date,
                    format1
                ), format2
            )
        }

        fun convertDateToString(date: Date?, format_value: String?): String? {
            val formats = SimpleDateFormat(format_value, Locale.ENGLISH)
            // SimpleDateFormat formats = new SimpleDateFormat(format_value);
            return formats.format(date)
        }

        fun convertStringToDate(dtStart: String?, format_value: String?): Date? {
            val format = SimpleDateFormat(format_value, Locale.ENGLISH)
            try {
                return format.parse(dtStart)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return Date()
        }

        fun changeSelected(view: LinearLayout, currentSelected: View?) {
            val childCount = view.childCount
            for (i in 0 until childCount) {
                val v = view.getChildAt(i)
                v.isSelected = false
            }
            if (currentSelected != null) {
                currentSelected.isSelected = true
            }
        }


        fun playMusice(context: Context?, id: Int): MediaPlayer? {
            val mediaPlayer = MediaPlayer.create(context, id)
            mediaPlayer.start()
            return mediaPlayer
        }

        @Throws(JSONException::class, NullPointerException::class)
        fun getJsonFromDeepLink(dynamicLink: Uri): JSONObject {
            val data = Objects.requireNonNull(dynamicLink.getQueryParameter("data"))?.toByteArray(
                StandardCharsets.UTF_8
            )
            val base64Byte = Base64.decode(data, Base64.DEFAULT)
            val text = String(base64Byte, StandardCharsets.UTF_8)
            return JSONObject(text)
        }

        fun toastMessage(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        @JvmStatic
        fun changeLanguage(context: Context, lang: String) {
            val res: Resources = context.resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.setLocale(Locale(lang.toLowerCase())) // API 17+ only.
            val locale = Locale(lang.toLowerCase())
            Locale.setDefault(locale)
            res.updateConfiguration(conf, dm)

        }

        fun handleErrorResponse(responseBody: ResponseBody, activity: Activity) {
            try {
                val error = Gson().fromJson(responseBody.string(), ErrorResponse::class.java)
                if (error.errors != null && error.errors.size > 0) {
                    showAlert(
                        activity,
                        error.errors.get(0),
                        R.drawable.ic_baseline_cancel_24
                    )
                } else {
                    showAlert(
                        activity,
                        error.message,
                        R.drawable.ic_baseline_cancel_24
                    )
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}