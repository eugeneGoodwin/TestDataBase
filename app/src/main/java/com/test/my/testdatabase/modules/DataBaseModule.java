package com.test.my.testdatabase.modules;


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.test.my.testdatabase.db.DBOpenHelper;
import com.test.my.testdatabase.entities.Post;
import com.test.my.testdatabase.entities.PostSQLiteTypeMapping;
import com.test.my.testdatabase.entities.User;
import com.test.my.testdatabase.entities.UserSQLiteTypeMapping;
import com.test.my.testdatabase.models.DataBaseModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {
    @Provides
    @NonNull
    @Singleton
    public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(Post.class, new PostSQLiteTypeMapping())
                .addTypeMapping(User.class, new UserSQLiteTypeMapping())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Context context) {
        return new DBOpenHelper(context);
    }

    @Provides
    @NonNull
    @Singleton
    public DataBaseModel provideDataBaseModel(@NonNull StorIOSQLite storIOSQLite) {
        return new DataBaseModel(storIOSQLite);
    }
}
