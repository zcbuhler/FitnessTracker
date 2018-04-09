package com.z.buhler.fitnesstracker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {


    private TextView mLoginStatusFragTV;

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

        mLoginStatusFragTV = (TextView) findViewById(R.id.display_user_fragment_text);

        mLoginButton = (Button) findViewById(R.id.login_button_login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isPasswordCorrect ()) {
                    // !!! It the future refactor to accompidate other username, if other users are added
                    String fragmentLoggedUserBanner = getString(R.string.frag_text_user_logged) + " " + getString(R.string.login_edit_username);


                    mLoginStatusFragTV.setText(fragmentLoggedUserBanner);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.logout_menu_option){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle("Message:");

            alertDialogBuilder.setMessage("Logging you out!").setCancelable(false);

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            String loggedOutBanner = getString(R.string.frag_text_user_logged);
                            mLoginStatusFragTV.setText(loggedOutBanner);
                        }
                    });

            alertDialog.show();

            return true;
        }


        return super.onOptionsItemSelected(item);

    }

    // *** Will need to be updated after an auth for multiuser is supported
    // or when password is stored outside of this file
    private boolean isPasswordCorrect () {

        int messageResId = 0;
        mLoginPasswordField =  (EditText) findViewById(R.id.login_edit_password);
        String enteredPassword = mLoginPasswordField.getText().toString();

        if (mPassword.equals(enteredPassword)){

            messageResId = R.string.login_successful;
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
            return true;

        } else {

            messageResId = R.string.login_unsuccessful;
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    // Will be used for auth of password in the future
    private void getUsersPassword(String userName) {

    }
}
