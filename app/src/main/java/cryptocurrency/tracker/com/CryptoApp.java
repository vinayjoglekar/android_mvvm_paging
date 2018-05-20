package cryptocurrency.tracker.com;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class CryptoApp extends Application {

    public static CryptoApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Stetho.initializeWithDefaults(this);
    }

}
