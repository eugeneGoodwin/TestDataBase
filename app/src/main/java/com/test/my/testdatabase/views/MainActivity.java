package com.test.my.testdatabase.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.test.my.testdatabase.R;
import com.test.my.testdatabase.presenters.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private MainPresenter mPresenter;

    @BindView(R.id.update_button)
    Button updateButton;

    @BindView(R.id.add_post_button)
    Button addPostButton;

    @BindView(R.id.add_user_button)
    Button addUserButton;

    @BindView(R.id.text_view)
    TextView textView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter();

        RxView.clicks(updateButton).subscribe(v -> mPresenter.start());
        RxView.clicks(addPostButton).subscribe(v -> mPresenter.addPost());
        RxView.clicks(addUserButton).subscribe(v -> mPresenter.addUser());

        mPresenter.attachView(this);
        mPresenter.init();
    }

    @Override
    public void onDestroy(){
        mPresenter.stop();
        super.onDestroy();
    }

    @Override
    public RecyclerView getRecyclerView(){
        return recyclerView;
    }

    @Override
    public TextView getStatus(){
        return textView;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
