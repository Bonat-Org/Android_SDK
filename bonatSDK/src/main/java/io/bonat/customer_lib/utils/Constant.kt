package io.bonat.customer_lib.utils

object Constant {
    const val URL_FIREBASE = "https://us-central1-bonatdev.cloudfunctions.net/updateOrder?env=dev"

    // private const val PRODUCTION_URL = "https://api.bonat.io/"
    private const val DEVELOPMENT_HOST_NAME = "http://52.15.230.101:"
    private const val STAGING_HOST_NAME = "https://stg-api.bonat.io/"

    private const val PORT = "3000/"
    private const val VERSION_2 = "customer/v2/"
    private const val VERSION_3 = "customer/v3/"

    //const val URL = DEVELOPMENT_HOST_NAME + PORT
    const val URL = STAGING_HOST_NAME

    const val REQUEST_URL = URL + VERSION_2
    const val REQUEST_URL_V3 = URL + VERSION_3

    ///////////////////////////API/////////////////////////////////
    const val LOYAL_MERCHANT = "loyalMerchant"
    const val COUPONS = "coupons"
    const val INBOX = "inbox"
    const val POINT_REDEMPTION = "pointRedemption"
    const val SDK_AUTH = REQUEST_URL_V3 + "sdk/auth"
    const val SDK_AUTH_CUSTOMER = REQUEST_URL_V3 + "sdk/auth/customer"

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

}