package io.bonat.customer_lib.di.component;



import dagger.Subcomponent;
import io.bonat.customer_lib.di.scopes.ActivityScope;
import io.bonat.customer_lib.ui.base.BaseActivity;
import io.bonat.customer_lib.ui.merchant.MerchantActivity;

@ActivityScope
@Subcomponent()
public interface ActivityComponent {


    void inject(BaseActivity baseActivity);
    void inject(MerchantActivity rewardActivity);

    @Subcomponent.Builder
    interface Builder {

        ActivityComponent build();
    }
}