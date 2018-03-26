package com.z.buhler.fitnesstracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button mLoginButton;
    private EditText mLoginPasswordField;

    // These variable will be updated later and stored outside of this file.
    // This is just used to test the toasts at this time.
    private String mUserName = "jdoe";
    private String mPassword = "welcome1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginButton = (Button) findViewById(R.id.login_button_login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordCorrect ();
            }
        });

    }

    // *** Will need to be updated after an auth for multiuser is supported
    // or when password is stored outside of this file
    private void isPasswordCorrect () {

        int messageResId = 0;
        mLoginPasswordField =  (EditText) findViewById(R.id.login_edit_password);
        String enteredPassword = mLoginPasswordField.getText().toString();

        if (mPassword.equals(enteredPassword)){

            messageResId = R.string.login_successful;

        } else {

            messageResId = R.string.login_unsuccessful;

        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        ;
    }

    // Will be used for auth of password in the future
    private void getUsersPassword(String userName) {

    }
}
