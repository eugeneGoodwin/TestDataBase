package com.test.my.testdatabase.presenters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.test.my.testdatabase.Application;
import com.test.my.testdatabase.adapters.RecycleViewAdapter;
import com.test.my.testdatabase.dialogs.PostDialog;
import com.test.my.testdatabase.dialogs.UserDialog;
import com.test.my.testdatabase.entities.Post;
import com.test.my.testdatabase.entities.User;
import com.test.my.testdatabase.interfaces.Callback;
import com.test.my.testdatabase.models.DataBaseModel;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BaseProgressPresenter<MainPresenter.View>{

    @Inject
    DataBaseModel dbModel;

    RecycleViewAdapter postAdapter;

    public MainPresenter(){
    }

    public void init(){

        Application.getApplication().getAppComponent().inject(this);

        RecyclerView recyclerView = view.getRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        postAdapter = new RecycleViewAdapter(null);
        recyclerView.setAdapter(postAdapter);
        update();
    }

    public void update(){
        showProgress();
        dbModel.getPosts(new Callback<List<Post>>() {
            @Override
            public void T(List<Post> list) {
                hideProgress();
                postAdapter.setPosts(list);
            }
        }, new Callback<Throwable>() {
            @Override
            public void T(Throwable ex) {
                error(ex);
            }
        });
    }

    public void start() {
        update();
    }

    public void addPost(){
        dbModel.getUsers(new Callback<List<User>>() {
            @Override
            public void T(List<User> list) {
                PostDialog post_dialog = new PostDialog(view.getContext(), list, new PostDialog.OnAddListener() {
                    @Override
                    public void onAdd(Post post) {
                        dbModel.addPost(post.userId(), post.title(), post.body(), new Callback<Boolean>() {
                            @Override
                            public void T(Boolean aBoolean) {
                                if (aBoolean)
                                    update();
                            }
                        });
                    }
                });
                post_dialog.show();
            }
        });
    }

    public void addUser(){
        UserDialog user_dialog = new UserDialog(view.getContext(), new UserDialog.OnAddListener(){
            @Override
            public void onAdd(User user) {
                dbModel.addUser(user.name(), user.username(), user.email(), user.phone(), new Callback<Boolean>() {
                    @Override
                    public void T(Boolean aBoolean) {
                        if(aBoolean)
                            update();
                    }
                });
            }
        });
        user_dialog.show();

    }

    public void stop(){
        if(dbModel != null) dbModel.stop();
    }

    @Override
    public void error(Throwable ex){
        super.error(ex);
        if(ex != null) {
            TextView errorView = view.getStatus();
            errorView.setText(ex.getMessage());
        }
    }

    public interface View extends BasePresenterInterface {
        RecyclerView getRecyclerView();
        TextView getStatus();
    }
}
