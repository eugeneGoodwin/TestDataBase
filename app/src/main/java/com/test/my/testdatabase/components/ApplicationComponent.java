package com.test.my.testdatabase.components;


import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.test.my.testdatabase.models.DataBaseModel;
import com.test.my.testdatabase.modules.AppModule;
import com.test.my.testdatabase.modules.DataBaseModule;
import com.test.my.testdatabase.presenters.MainPresenter;
import com.test.my.testdatabase.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                DataBaseModule.class
        }
)
public interface ApplicationComponent {
    void inject(@NonNull MainPresenter presenter);
}
