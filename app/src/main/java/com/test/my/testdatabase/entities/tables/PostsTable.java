package com.test.my.testdatabase.entities.tables;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.queries.Query;

public class PostsTable {

    @NonNull
    public static final String TABLE = "posts";

    @NonNull
    public static final String COLUMN_ID = "_id";

    @NonNull
    public static final String COLUMN_USERID = "userId";

    @NonNull
    public static final String COLUMN_TITLE = "title";

    @NonNull
    public static final String COLUMN_BODY = "body";

    public static final String COLUMN_ID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_ID;
    public static final String COLUMN_USERID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_USERID;
    public static final String COLUMN_TITLE_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_TITLE;
    public static final String COLUMN_BODY_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_BODY;

    // Yep, with StorIO you can safely store queries as objects and reuse them, they are immutable
    @NonNull
    public static final Query QUERY_ALL = Query.builder()
            .table(TABLE)
            .build();

    // This is just class with Meta Data, we don't need instances
    private PostsTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_USERID + " INTEGER NOT NULL, "
                + COLUMN_TITLE + " TEXT NOT NULL, "
                + COLUMN_BODY + " TEXT NOT NULL"
                + ");";
    }
}
