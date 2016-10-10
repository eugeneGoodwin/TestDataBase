package com.test.my.testdatabase.dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.test.my.testdatabase.R;
import com.test.my.testdatabase.entities.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDialog {
    final AlertDialog dialog;

    @BindView(R.id.name_edit)
    EditText mNameEditText;

    @BindView(R.id.username_edit)
    EditText mUserNameEditText;

    @BindView(R.id.email_edit)
    EditText mEmailEditText;

    @BindView(R.id.phone_edit)
    EditText mPhoneEditText;

    public OnAddListener mAddListener;

    public UserDialog(Context context, OnAddListener addListener) {
        mAddListener = addListener;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_user, null);
        ButterKnife.bind(this, view);

        Button button_cancel = (Button) view.findViewById(R.id.button1);
        button_cancel.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });

        Button button_ok = (Button) view.findViewById(R.id.button2);
        button_ok.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String name = mNameEditText.getText().toString();
                String username = mUserNameEditText.getText().toString();
                String email = mEmailEditText.getText().toString();
                String phone = mPhoneEditText.getText().toString();

                if(name.equals(""))
                    return;

                User user = User.newUser(name, username, email, phone);
                if (mAddListener != null) {
                    mAddListener.onAdd(user);
                    dialog.dismiss();
                }
            }
        });

        dialog = new AlertDialog.Builder(context).setTitle("Add user").setView(view).create();
    }

    public void show(){
        dialog.show();
    }

    public interface OnAddListener {
        void onAdd(User user);
    }

}
