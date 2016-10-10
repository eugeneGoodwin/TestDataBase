package com.test.my.testdatabase.entities;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.test.my.testdatabase.entities.tables.PostsTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

@StorIOSQLiteType(table = PostsTable.TABLE)
public class Post {

    @Nullable
    @StorIOSQLiteColumn(name = PostsTable.COLUMN_ID, key = true)
    Long id;

    @NonNull
    @StorIOSQLiteColumn(name = PostsTable.COLUMN_USERID)
    Long userId;

    @NonNull
    @StorIOSQLiteColumn(name = PostsTable.COLUMN_TITLE)
    String title;

    @NonNull
    @StorIOSQLiteColumn(name = PostsTable.COLUMN_BODY)
    String body;

    Post() {
    }

    private Post(@Nullable Long id, @NonNull Long userId, @NonNull String title, @NonNull String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    @NonNull
    public static Post newPost(@Nullable Long id, @NonNull Long userId, @NonNull String title, @NonNull String body) {
        return new Post(id, userId, title, body);
    }

    @NonNull
    public static Post newPost(@NonNull Long userId, @NonNull String title, @NonNull String body) {
        return new Post(null, userId, title, body);
    }

    @Nullable
    public Long id() {
        return id;
    }

    @NonNull
    public Long userId() {
        return userId;
    }

    @NonNull
    public String title() {
        return title;
    }

    @NonNull
    public String body() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != null ? !id.equals(post.id) : post.id != null) return false;
        if (!userId.equals(post.userId)) return false;
        if (!title.equals(post.title)) return false;
        return body.equals(post.body);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + userId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
