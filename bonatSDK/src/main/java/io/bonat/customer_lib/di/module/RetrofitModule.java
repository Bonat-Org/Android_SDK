package io.bonat.customer_lib.di.module;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;


import com.app.easytable.ui.base.ViewModelFactory;

import java.util.Locale;
import java.util.Objects;

import dagger.Module;
import dagger.Provides;
import io.bonat.customer_lib.data.local.PreferencesHelper;
import io.bonat.customer_lib.di.scopes.ApplicationScope;
import io.bonat.customer_lib.retrofit.APIInterface;
import io.bonat.customer_lib.utils.LocaleHelper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class RetrofitModule {
    private final Application mApplication;
    private String mBaseUrl;

    public RetrofitModule(Application app, String mBaseUrl) {
        mApplication = app;
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    APIInterface getApiInterface(Retrofit retroFit) {
        return retroFit.create(APIInterface.class);
    }

    @Provides
    @ApplicationScope
    Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)//"https://jsonplaceholder.typicode.com/"
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @ApplicationScope
    OkHttpClient getOkHttpCleint(HttpLoggingInterceptor httpLoggingInterceptor) {
        // Log.e("DataManager",""+getPreferencesHelper().getCurrentUser().getToken());

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addNetworkInterceptor(chain -> {
                    Request request = chain.request();
                    Request.Builder builder1 = request.newBuilder()
                            //   .addHeader("Accept-Language", Objects.requireNonNull(getPreferencesHelper().getStringWithKey(LANGUAGE, Locale.getDefault().getLanguage())))
                            .addHeader("Accept-Language", "en")
                            .addHeader("Accept", "application/json; charset=utf-8")
                            .addHeader("Content-Type", "application/x-www-form-urlencoded");
                    // .addHeader("Authorization", "Bearer f56c116dfd926716daff1ed8ed59c88e32c3ab854bcb4d8eee7e4cf6011f2f92a84c1a100d46f5bdb8819bfd6d8235feda3c8b5044268f3bd6ccade08b282de6");
                    if (getPreferencesHelper().getCurrentUser() != null) {
                        builder1.addHeader("Authorization", "Bearer " + getPreferencesHelper().getCurrentUser().getToken());
                    }
                    request = builder1.build();
                    return chain.proceed(request);
                })
                .addInterceptor(httpLoggingInterceptor);

        return builder.build();
    }

    @Provides
    @ApplicationScope
    PreferencesHelper getPreferencesHelper() {
        return new PreferencesHelper(mApplication);
    }

    @Provides
    @ApplicationScope
    LocaleHelper getLocaleHelper() {
        return new LocaleHelper();
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    @ApplicationScope
    ViewModelProvider.Factory getViewModelFactory() {
        return new ViewModelFactory();
    }
}
