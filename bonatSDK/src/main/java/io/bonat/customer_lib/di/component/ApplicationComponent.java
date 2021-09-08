package io.bonat.customer_lib.di.component;

import android.content.Context;


import dagger.Component;
import io.bonat.customer_lib.LibApplication;
import io.bonat.customer_lib.data.DataManager;
import io.bonat.customer_lib.data.local.PreferencesHelper;
import io.bonat.customer_lib.di.module.ContextModule;
import io.bonat.customer_lib.di.module.RetrofitModule;
import io.bonat.customer_lib.di.qualifier.ApplicationContext;
import io.bonat.customer_lib.di.scopes.ApplicationScope;
import io.bonat.customer_lib.retrofit.APIInterface;
import io.bonat.customer_lib.utils.LocaleHelper;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public APIInterface getApiInterface();

    public PreferencesHelper preferencesHelper();

    DataManager dataManager();

    LocaleHelper language_utils();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(LibApplication myApplication);


    ActivityComponent.Builder getActivityComponentBuilder();

}
