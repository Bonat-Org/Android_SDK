package io.bonat.customer_lib.utils

import android.util.Log
import io.bonat.customer_lib.data.model.Mode

object Constant {

    const val URL_FIREBASE = "https://us-central1-bonatdev.cloudfunctions.net/updateOrder?env=dev"

    const val PRODUCTION_URL = "https://api.bonat.io/"
    private const val PORT = "3000/"
    const val DEVELOPMENT_HOST_NAME = "http://52.15.230.101:$PORT"
    const val STAGING_HOST_NAME = "https://stg-api.bonat.io/"

    var URL = STAGING_HOST_NAME

    private const val VERSION_2 = "customer/v2/"
    private const val VERSION_3 = "customer/v3/"

    var REQUEST_URL = URL + VERSION_2
    var REQUEST_URL_V3 = URL + VERSION_3

    ///////////////////////////API/////////////////////////////////
    const val LOYAL_MERCHANT = "loyalMerchant"
    const val COUPONS = "coupons"
    const val INBOX = "inbox"
    const val POINT_REDEMPTION = "pointRedemption"
    const val SDK_AUTH = "sdk/auth"
    const val CUSTOMER_INFO = "sdk/auth/customer"

    @JvmField
    var REQUEST_SDK_AUTH = REQUEST_URL_V3 + SDK_AUTH

    @JvmField
    var SDK_AUTH_CUSTOMER = REQUEST_URL_V3 + CUSTOMER_INFO

    /////////////////pref////////////////////////////////
    const val REWARD_MENU = "Reward Menu"
    const val COLLECT_REWARD = "collect reward"
    const val COUPON_LIMIT = "couponLimit"
    const val COUPON_OFFSET = "couponOffset"
    const val ID_MERCHANT = "idMerchant"
    const val ID_CAMPAIGN = "idCampaign"
    const val MESSAGE = "message"
    const val RATE = "rate"
    const val ID_ORDER = "idOrder"
    const val ID_CUSTOMER = "idCustomer"
    const val DATA = "data"
    const val SDK_TOKEN = "Sdk_token"
    const val SDK = "sdk"
    const val AUTHORIZATION = "Authorization"
    const val SECRET = "secret"
    const val PHONE_NUMBER = "phoneNumber"
    const val OS = "os"
    const val FORMAT_YYYY_MM_DD_T_HH_MM_SS = "yyyy'-'MM'-'dd'T'HH':'mm':'ss.SSS'Z'"
    const val FORMAT_MMM_D_YYYY_H_MM_A = "MMM d, yyyy h:mm a"
    const val ANDROID = "android"

    @JvmStatic
    fun retUrlHost(mode: Mode) {
        Log.e("http300",""+mode)

        when (mode) {
            Mode.DEVELOPMENT -> {
                REQUEST_URL =  DEVELOPMENT_HOST_NAME + VERSION_2
                REQUEST_URL_V3 =  DEVELOPMENT_HOST_NAME + VERSION_3
            }
            Mode.PRODUCTION -> {
                REQUEST_URL =    PRODUCTION_URL + VERSION_2
                REQUEST_URL_V3 =  PRODUCTION_URL + VERSION_3
            }
            Mode.STAGING -> {
                REQUEST_URL =   STAGING_HOST_NAME + VERSION_2
                REQUEST_URL_V3 =  STAGING_HOST_NAME + VERSION_3
            }
        }
        REQUEST_SDK_AUTH=REQUEST_URL_V3+ SDK_AUTH
        SDK_AUTH_CUSTOMER=REQUEST_URL_V3+ CUSTOMER_INFO
        Log.e("http30",""+REQUEST_SDK_AUTH)

    }

}