package io.bonat.customer_lib.data;


import android.content.Context;


import java.util.HashMap;

import javax.inject.Inject;

import io.bonat.customer_lib.data.local.PreferencesHelper;
import io.bonat.customer_lib.data.model.Coupon;
import io.bonat.customer_lib.data.model.Mercahnt;
import io.bonat.customer_lib.data.model.Wallet;
import io.bonat.customer_lib.di.qualifier.ApplicationContext;
import io.bonat.customer_lib.retrofit.APIInterface;
import io.bonat.customer_lib.utils.LocaleHelper;
import io.reactivex.Observable;
import retrofit2.Call;

import static io.bonat.customer_lib.utils.Constant.COUPON_LIMIT;
import static io.bonat.customer_lib.utils.Constant.COUPON_OFFSET;


public class DataManager {

    private final APIInterface apiService;
    private final PreferencesHelper mPreferencesHelper;
    Context mContext;
    String token;
    private final LocaleHelper languageUtils;


    @Inject
    public DataManager(PreferencesHelper preferencesHelper, LocaleHelper languageUtils, @ApplicationContext Context context, APIInterface homeServiceApi) {
        this.apiService = homeServiceApi;
        mPreferencesHelper = preferencesHelper;
        this.languageUtils = languageUtils;
        mContext = context;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public LocaleHelper getLanguageUtils() {
        return languageUtils;
    }


    public Observable<BaseResponseObject<Mercahnt>> showMerchantData(String id) {
        return apiService.showMerchantData(id)
                .concatMap(response2 -> Observable.create(e -> {
                    try {
                        e.onNext(response2);
                    } catch (Exception e1) {
                        e.onError(e1);
                    }
                    e.onComplete();
                }));
    }

    public Observable<BaseResponseObject<Wallet>> getWalletData() {
        HashMap<String, String> hash = new HashMap<>();
        hash.put(COUPON_LIMIT, "20");
        hash.put(COUPON_OFFSET, "0");
        return apiService.getWalletData(hash)
                .concatMap(response2 -> Observable.create(e -> {
                    try {
                        e.onNext(response2);
                    } catch (Exception e1) {
                        e.onError(e1);
                    }
                    e.onComplete();
                }));
    }

    public Observable<BaseResponse> setFeedBack(HashMap<String, String> data) {
        return apiService.setFeedBack(data)
                .concatMap(response2 -> Observable.create(e -> {
                    try {
                        e.onNext(response2);
                    } catch (Exception e1) {
                        e.onError(e1);
                    }
                    e.onComplete();
                }));
    }

    public Observable<BaseResponseArray<String>> updateOrder(String url, HashMap<String, String> data) {
        return apiService.updateOrder(url, data)
                .concatMap(response2 -> Observable.create(e -> {
                    try {
                        e.onNext(response2);
                    } catch (Exception e1) {
                        e.onError(e1);
                    }
                    e.onComplete();
                }));
    }

    public Call<BaseResponseArray<String>> updateOrder2(String url, HashMap<String, String> data) {
        return apiService.updateOrder2(url, data);
    }

    public Observable<BaseResponseObject<Coupon>> pointRedemption(String id) {
        return apiService.pointRedemption(id)
                .concatMap(response2 -> Observable.create(e -> {
                    try {
                        e.onNext(response2);
                    } catch (Exception e1) {
                        e.onError(e1);
                    }
                    e.onComplete();
                }));
    }
}