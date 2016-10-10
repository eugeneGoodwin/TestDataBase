package com.test.my.testdatabase;

import com.test.my.testdatabase.components.ApplicationComponent;
import com.test.my.testdatabase.components.DaggerApplicationComponent;
import com.test.my.testdatabase.modules.AppModule;
import com.test.my.testdatabase.modules.DataBaseModule;

public class Application extends android.app.Application {
    static Application mApplication;
    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        mAppComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .dataBaseModule(new DataBaseModule())
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }

    public static Application getApplication(){ return mApplication; }
}
