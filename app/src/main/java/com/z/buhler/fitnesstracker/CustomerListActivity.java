package com.z.buhler.fitnesstracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomerListActivity extends AppCompatActivity {

    private Button mViewProfileButton;
    private Button mAddCustomerButton;
    private TextView mLoginStatusFragTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        mLoginStatusFragTV = (TextView) findViewById(R.id.display_user_fragment_text);
        mLoginStatusFragTV.setText(R.string.user_logged_in);


        mViewProfileButton = (Button) findViewById(R.id.customer_list_button_view_profile);
        mViewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomerListActivity.this, CustomerProfileActivity.class);
                startActivity(intent);
            }
        });

        mAddCustomerButton = (Button) findViewById(R.id.customer_list_button_add);
        mAddCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomerListActivity.this, AddCustomerActivity.class);
                startActivity(intent);
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

                            Intent intent = new Intent(CustomerListActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });

            alertDialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
