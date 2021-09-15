package io.bonat.customer_lib.retrofit;

import java.util.ArrayList;
import java.util.HashMap;

import io.bonat.customer_lib.data.BaseResponse;
import io.bonat.customer_lib.data.BaseResponseArray;
import io.bonat.customer_lib.data.BaseResponseObject;
import io.bonat.customer_lib.data.model.Campaign;
import io.bonat.customer_lib.data.model.Coupon;
import io.bonat.customer_lib.data.model.Mercahnt;
import io.bonat.customer_lib.data.model.UpdateOrder;
import io.bonat.customer_lib.data.model.Wallet;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

import static io.bonat.customer_lib.utils.Constant.COUPONS;
import static io.bonat.customer_lib.utils.Constant.ID_CAMPAIGN;
import static io.bonat.customer_lib.utils.Constant.INBOX;
import static io.bonat.customer_lib.utils.Constant.LOYAL_MERCHANT;
import static io.bonat.customer_lib.utils.Constant.POINT_REDEMPTION;

public interface APIInterface {


    @GET(LOYAL_MERCHANT + "/{ID}")
    Observable<BaseResponseObject<Mercahnt>> showMerchantData(@Path(value = "ID", encoded = true) String id);

    @GET(COUPONS)
    Observable<BaseResponseObject<Wallet>> getWalletData(@QueryMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST(INBOX)
    Observable<BaseResponse> setFeedBack(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST
    Observable<BaseResponseArray<String>> updateOrder(@Url String url, @FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST
    Call<BaseResponseArray<String>> updateOrder2(@Url String url, @FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST(POINT_REDEMPTION)
    Observable<BaseResponseObject<Coupon>> pointRedemption(@Field(ID_CAMPAIGN) String id);

}
