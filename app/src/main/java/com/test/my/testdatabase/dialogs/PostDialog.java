package com.test.my.testdatabase.dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.test.my.testdatabase.R;
import com.test.my.testdatabase.entities.Post;
import com.test.my.testdatabase.entities.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;


public class PostDialog {
    final AlertDialog dialog;

    @BindView(R.id.title_edit)
    EditText mTitleEditText;

    @BindView(R.id.body_edit)
    EditText mBodyEditText;

    @BindView(R.id.spinner)
    Spinner mSpinner;

    public OnAddListener mAddListener;
    private List<User> users;

    public PostDialog(Context context, List<User> users, OnAddListener addListener) {
        this.users = users;
        mAddListener = addListener;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_post, null);
        ButterKnife.bind(this, view);

        List<String> user_names = Observable.from(users)
                                            .map(user -> user.name())
                                            .toList()
                                            .toBlocking()
                                            .first();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner_center_item, user_names);
        adapter.setDropDownViewResource(R.layout.spinner_center_item);

        mSpinner.setAdapter(adapter);

        Button button_cancel = (Button) view.findViewById(R.id.button3);
        button_cancel.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });

        Button button_ok = (Button) view.findViewById(R.id.button4);
        button_ok.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String title = mTitleEditText.getText().toString();
                String body = mBodyEditText.getText().toString();
                String userName = mSpinner.getSelectedItem().toString();

                User selected_user = Observable.from(users)
                        .filter(user -> user.name().equals(userName))
                        .toBlocking()
                        .first();

                Post post = Post.newPost(selected_user.id(), title, body);
                if (mAddListener != null) {
                    mAddListener.onAdd(post);
                    dialog.dismiss();
                }
            }
        });

        dialog = new AlertDialog.Builder(context).setTitle("Add post").setView(view).create();
    }

    public void show(){
        dialog.show();
    }

    public interface OnAddListener {
        void onAdd(Post post);
    }

}
