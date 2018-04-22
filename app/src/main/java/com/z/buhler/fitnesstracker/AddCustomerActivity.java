package com.z.buhler.fitnesstracker;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
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
import android.widget.ImageView;
import android.widget.TextView;
import com.z.buhler.fitnesstracker.CustomerLab;
import com.z.buhler.fitnesstracker.database.CustomerBaseHelper;
import com.z.buhler.fitnesstracker.database.CustomerDbSchema;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.z.buhler.fitnesstracker.CustomerProfileActivity.REQUEST_IMAGE_CAPTURE;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.*;

import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.ADDRESS;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.CREDIT_CARD_NUMBER;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.EMAIL;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.EMAIL_RECEIPT;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.FULL_NAME;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.PRINT_RECEIPT;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.SESSIONS_REMAINING;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.UUID;

public class AddCustomerActivity extends AppCompatActivity {

    private TextView mLoginStatusFragTV;
    private Button mSubmitButton;
    private ImageView mAddPictureIV;

    private Context mContext;

    private EditText mNameET;
    private EditText mAddressET;
    private EditText mCreditCardNumberET;
    private EditText mEmail;
    private EditText mSessionsPurchasedET;
    private CheckBox mEmailReceiptCB;
    private CheckBox mPrintReceiptCB;

    private String mCustomerImagePath;
    private String mCurrentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        mNameET = (EditText) findViewById(R.id.add_customer_text_name);
        mAddressET = (EditText) findViewById(R.id.add_customer_text_billing_address);
        mCreditCardNumberET = (EditText) findViewById(R.id.add_customer_number_credit_card_number);
        mEmail = (EditText) findViewById(R.id.add_customer_text_email);
        mSessionsPurchasedET = (EditText) findViewById(R.id.add_customer_number_sessions_purchase);
        mEmailReceiptCB = (CheckBox) findViewById(R.id.add_customer_email_receipt_checkbox);
        mPrintReceiptCB = (CheckBox) findViewById(R.id.add_customer_print_receipt_checkbox);

        mLoginStatusFragTV = (TextView) findViewById(R.id.display_user_fragment_text);
        mLoginStatusFragTV.setText(R.string.user_logged_in);

        mSubmitButton = (Button) findViewById(R.id.add_customer_submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 //Create the customer
                Customer newCustomer = new Customer();

                newCustomer.setName(mNameET.getText().toString());
                newCustomer.setAddress(mAddressET.getText().toString());
                newCustomer.setCreditCardNumber(mCreditCardNumberET.getText().toString());
                newCustomer.setEmail(mEmail.getText().toString());
                newCustomer.setSessionsRemaining(Integer.parseInt(mSessionsPurchasedET.getText().toString()));
                newCustomer.setEmailReceipt(mEmailReceiptCB.isChecked());
                newCustomer.setPrintReceipt(mPrintReceiptCB.isChecked());

                addCustomer(newCustomer);

            }
        });

        mAddPictureIV = (ImageView) findViewById(R.id.add_customer_add_photo_icon);

        mAddPictureIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                Log.d("PATH FOR IMAGE", "" + mCurrentPhotoPath);
            }
        });

    }

    public void onClick(View view) {
        Intent intent = new Intent(AddCustomerActivity.this, CustomerProfileActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");

            try {
                createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
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


    // Database functions below
    private static ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();
        values.put(UUID, customer.getId().toString());
        values.put(FULL_NAME, customer.getName());
        values.put(ADDRESS, customer.getAddress());
        values.put(CREDIT_CARD_NUMBER, customer.getCreditCardNumber().toString());
        values.put(EMAIL, customer.getEmail());
        values.put(SESSIONS_REMAINING, customer.getSessionsRemaining());
        values.put(PRINT_RECEIPT, customer.getPrintReceipt() ? 1 : 0);
        values.put(EMAIL_RECEIPT, customer.getEmailReceipt() ? 1 : 0);
        return values;
    }

    public void addCustomer(Customer c) {

        ContentValues values = getContentValues(c);
        SQLiteDatabase mDatabase = new CustomerBaseHelper(this ).getReadableDatabase();
        mDatabase.insert(CustomerDbSchema.CustomerTable.NAME, null, values);

    }

    private void dispatchTakePictureIntent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_IMAGE_CAPTURE);
            }
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {


            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.z.buhler.fitnesstracker.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }



}
