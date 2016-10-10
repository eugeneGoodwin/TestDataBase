package com.test.my.testdatabase.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;
import com.test.my.testdatabase.entities.tables.UsersTable;

@StorIOSQLiteType(table = UsersTable.TABLE)
public class User {

    @Nullable
    @StorIOSQLiteColumn(name = UsersTable.COLUMN_ID, key = true)
    Long id;

    @NonNull
    @StorIOSQLiteColumn(name = UsersTable.COLUMN_NAME)
    String name;

    @NonNull
    @StorIOSQLiteColumn(name = UsersTable.COLUMN_USERNAME)
    String username;

    @NonNull
    @StorIOSQLiteColumn(name = UsersTable.COLUMN_EMAIL)
    String email;

    @NonNull
    @StorIOSQLiteColumn(name = UsersTable.COLUMN_PHONE)
    String phone;

    User() {
    }

    @NonNull
    public static User newUser(@Nullable Long id, @NonNull String name, @NonNull String username, @NonNull String email, @NonNull String phone) {
        User user = new User();
        user.id = id;
        user.name = name;
        user.username = username;
        user.email = email;
        user.phone = phone;
        return user;
    }

    @NonNull
    public static User newUser(@NonNull String name, @NonNull String username, @NonNull String email, @NonNull String phone) {
        return newUser(null, name, username, email, phone);
    }

    @Nullable
    public Long id() {
        return id;
    }

    @NonNull
    public String name() {
        return name;
    }

    @NonNull
    public String username() {
        return username;
    }

    @NonNull
    public String email() {
        return email;
    }

    @NonNull
    public String phone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (!name.equals(user.name)) return false;
        if (!username.equals(user.username)) return false;
        if (!email.equals(user.email)) return false;
        return phone.equals(user.phone);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
