package io.bonat.customer_lib.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.bonat.customer_lib.di.qualifier.ApplicationContext;
import io.bonat.customer_lib.di.scopes.ApplicationScope;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
