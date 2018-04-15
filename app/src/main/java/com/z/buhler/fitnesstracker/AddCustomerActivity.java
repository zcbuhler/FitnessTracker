package com.z.buhler.fitnesstracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class AddCustomerActivity extends AppCompatActivity {

    private TextView mLoginStatusFragTV;
    private Button mSubmitButton;

    private EditText mNameET;
    private EditText mAddressET;
    private EditText mCreditCardNumberET;
    private EditText mEmail;
    private EditText mSessionPurchasedET;
    private CheckBox mEmailReceiptCB;
    private CheckBox mPrintReceiptCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        mNameET = (EditText) findViewById(R.id.add_customer_text_name);
        mAddressET = (EditText) findViewById(R.id.add_customer_text_billing_address);
        mCreditCardNumberET = (EditText) findViewById(R.id.add_customer_number_credit_card_number);
        mEmail = (EditText) findViewById(R.id.add_customer_text_email);
        mSessionPurchasedET = (EditText) findViewById(R.id.add_customer_number_sessions_purchase);
        mEmailReceiptCB = (CheckBox) findViewById(R.id.add_customer_email_receipt_checkbox);
        mPrintReceiptCB = (CheckBox) findViewById(R.id.add_customer_print_receipt_checkbox);


        mLoginStatusFragTV = (TextView) findViewById(R.id.display_user_fragment_text);
        mLoginStatusFragTV.setText(R.string.user_logged_in);

        mSubmitButton = (Button) findViewById(R.id.add_customer_submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create the customer
                Customer newCustomer = new Customer();

                newCustomer.setName(mNameET.getText().toString());
                newCustomer.setAddress(mAddressET.getText().toString());
                newCustomer.setCreditCardNumber(mCreditCardNumberET.getText().toString());
                newCustomer.setEmail(mEmail.getText().toString());
                newCustomer.setSessionsRemaining(Integer.parseInt(mSessionPurchasedET.getText().toString()));
                newCustomer.setEmailReceipt(mEmailReceiptCB.isChecked());
                newCustomer.setPrintReciept(mPrintReceiptCB.isChecked());

                Log.d("Customer Object Added", "" + newCustomer.getName() + "/n" + newCustomer.getAddress() + "/n" + newCustomer.getPrintReciept());
            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(AddCustomerActivity.this, CustomerProfileActivity.class);
        startActivity(intent);
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

                            Intent intent = new Intent(AddCustomerActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });

            alertDialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
