package io.bonat.sample_lib;

import io.bonat.customer_lib.BonatApplication;
import io.bonat.customer_lib.data.model.Mode;

public class SampleApp extends BonatApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initBonatSDK("merchantID", "secretKey", Mode.DEVELOPMENT);
    }
}
