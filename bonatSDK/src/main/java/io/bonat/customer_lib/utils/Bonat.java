package io.bonat.customer_lib.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import io.bonat.customer_lib.R;
import io.bonat.customer_lib.StartView;
import io.bonat.customer_lib.data.local.PreferencesHelper;
import io.bonat.customer_lib.data.model.Customer;
import io.bonat.customer_lib.data.model.MerchantSDK;

import static io.bonat.customer_lib.utils.Constant.AUTHORIZATION;
import static io.bonat.customer_lib.utils.Constant.DATA;
import static io.bonat.customer_lib.utils.Constant.ID_MERCHANT;
import static io.bonat.customer_lib.utils.Constant.OS;
import static io.bonat.customer_lib.utils.Constant.PHONE_NUMBER;
import static io.bonat.customer_lib.utils.Constant.SDK_AUTH;
import static io.bonat.customer_lib.utils.Constant.SDK_AUTH_CUSTOMER;
import static io.bonat.customer_lib.utils.Constant.SDK_TOKEN;
import static io.bonat.customer_lib.utils.Constant.SECRET;

public class Bonat {
    // public static void initializeSDK(Context context,String merchantId,String secret) {
    public static void initializeSDK(Context context, String merchantId, StartView startView) {
        KProgressHUD progress = ViewUtils.progressBar(context, context.getString(R.string.please_wait), true);
        assert progress != null;
        progress.show();
        JsonObject json = new JsonObject();
        json.addProperty(ID_MERCHANT, merchantId);
       // json.addProperty(SECRET, "2f4992dc88f66336");
        json.addProperty(SECRET, "7102273b683c8e7d");
        Log.e("http:_",SDK_AUTH);
        Ion.with(context)
                .load(SDK_AUTH)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback((e, result) -> {
                    PreferencesHelper preferencesHelper = new PreferencesHelper(context);
                    if (result.get(DATA) instanceof JsonObject) {
                        MerchantSDK data = new Gson().fromJson(result.getAsJsonObject(DATA), MerchantSDK.class);
                        preferencesHelper.addWithKey(SDK_TOKEN, data.getSdk_token());
                        Log.e("JsonObject", "" + data.getSdk_token());
                        getCustomerInfo(context, startView, progress);
                    } else {
                        Toast.makeText(context, result.getAsJsonArray("errors").get(0).toString(), Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }

                });

    }

    public static void getCustomerInfo(Context context, StartView startView, KProgressHUD progress) {
        PreferencesHelper preferencesHelper = new PreferencesHelper(context);

        JsonObject json = new JsonObject();
        json.addProperty(PHONE_NUMBER, "0512345678");
        json.addProperty(OS, "android");

        Ion.with(context)
                .load(SDK_AUTH_CUSTOMER)
                .setHeader(AUTHORIZATION, "Bearer " + preferencesHelper.getStringWithKey(SDK_TOKEN, null))
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback((e, result) -> {
                    Customer data = new Gson().fromJson(result.getAsJsonObject(DATA), Customer.class);
                    preferencesHelper.addUserSession(data);
                    Log.e("JsonObject2", "" + result);
                    progress.dismiss();
                    startView.finishSDK();
                });
    }

}
