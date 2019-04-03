package com.smartreader;

import android.app.Application;

import com.smartreader.network.AppComponent;
import com.smartreader.network.AppModule;
import com.smartreader.network.DaggerAppComponent;
import com.smartreader.network.NetWorkModule;

/**
 * Created by Nithin Chalakkal on 03-04-2019.
 */

public class SmartReaderApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netWorkModule(new NetWorkModule())
                .build();

    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}