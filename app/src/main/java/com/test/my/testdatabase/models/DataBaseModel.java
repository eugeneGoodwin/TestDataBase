package com.test.my.testdatabase.models;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.test.my.testdatabase.entities.Post;
import com.test.my.testdatabase.entities.User;
import com.test.my.testdatabase.entities.tables.PostsTable;
import com.test.my.testdatabase.entities.tables.UsersTable;
import com.test.my.testdatabase.interfaces.Callback;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;


public class DataBaseModel {

    StorIOSQLite storIOSQLite;
    final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public DataBaseModel(StorIOSQLite storIOSQLite){
        this.storIOSQLite = storIOSQLite;
    }

    public void getPosts(Callback<List<Post>> callback, Callback<Throwable> error){
        Subscription subscription = storIOSQLite
                .get()
                .listOfObjects(Post.class)
                .withQuery(PostsTable.QUERY_ALL)
                .prepare()
                .asRxObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts -> {
                            callback.T(posts);
                        },
                        ex -> error.T(ex)
                );
        unsubscribe(subscription);
    }

    public void getUsers(Callback<List<User>> callback){
        Subscription subscription = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(UsersTable.QUERY_ALL)
                .prepare()
                .asRxObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                            callback.T(users);
                        }
                );
        unsubscribe(subscription);
    }

    public void addPost(Long userId, String title, String body, Callback<Boolean> callback){
        Subscription subscription = storIOSQLite
                .put()
                .object(Post.newPost(userId, title, body))
                .prepare()
                .asRxObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> callback.T(result.wasInserted()));
        unsubscribe(subscription);
    }

    public void addUser(String name, String username, String email, String phone, Callback<Boolean> callback){
        Subscription subscription = storIOSQLite
                .put()
                .object(User.newUser(name, username, email, phone))
                .prepare()
                .asRxObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> callback.T(result.wasInserted()));
        unsubscribe(subscription);
    }

    public void addPosts(){
        final List<Post> posts = new ArrayList<Post>();

        posts.add(Post.newPost(1L, "Title1", "Body1"));
        posts.add(Post.newPost(2L, "Title2", "Body2"));
        posts.add(Post.newPost(3L, "Title3", "Body3"));

        storIOSQLite
                .put()
                .objects(posts)
                .prepare()
                .asRxObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> result.results());
    }

    protected void unsubscribe(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void stop(){
        compositeSubscription.clear();
    }
}
