package com.kangwang.word;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.deltadna.android.sdk.DDNA;

public class WordApplication extends Application {

    private final String ENVIRONMENT_KEY_LIVE = "45134504831694888975087550515913";
    private final String COLLECT_URL = "https://collect17019wrdsq.deltadna.net/collect/api";
    private final String ENGAGE_URL = "https://engage17019wrdsq.deltadna.net";
    private final String ENVIRONMENT_KEY_DEV = "45134488143531638121974242815913";
    public static boolean DEBUG = true;


    private static final String AF_DEV_KEY = "Tik3EpN5P49NDvUxMnsx24";

    @Override
    public void onCreate() {
        super.onCreate();
        PackageManager packageManager = this.getPackageManager();
        String versionName = "1.6";
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
            if(DEBUG) {
                DDNA.initialise(new DDNA.Configuration(
                        this,
                        ENVIRONMENT_KEY_DEV,
                        COLLECT_URL,
                        ENGAGE_URL).clientVersion(versionName)
                );
            }else{
                DDNA.initialise(new DDNA.Configuration(
                        this,
                        ENVIRONMENT_KEY_LIVE,
                        COLLECT_URL,
                        ENGAGE_URL).clientVersion(versionName)
                );
            }


    }

}
