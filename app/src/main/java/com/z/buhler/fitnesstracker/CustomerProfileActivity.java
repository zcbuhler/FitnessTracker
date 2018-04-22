package com.z.buhler.fitnesstracker;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CustomerProfileActivity extends AppCompatActivity {

    private TextView mSeletedCustomerNameTV;
    private TextView mNumberOfSessionsRemainingTV;

    private Customer mSelectedCustomer;

    private Button mToCustomerListButton;
    private Button mAddSessionsButton;
    private Button mSessionCompletedButton;

    private TextView mLoginStatusFragTV;
    private ImageView mProfilePicture;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        // Get the Customer that has been selected
        mSelectedCustomer = (Customer) getIntent().getSerializableExtra("SelectedCustomer");

        // Get the member display UI items
        mSeletedCustomerNameTV = (TextView) findViewById(R.id.customer_profile_name);
        mNumberOfSessionsRemainingTV = (TextView) findViewById(R.id.customer_profile_number_sessions_used);

        mProfilePicture = (ImageView) findViewById(R.id.profile_image_image_view);

        mLoginStatusFragTV = (TextView) findViewById(R.id.display_user_fragment_text);
        mLoginStatusFragTV.setText(R.string.user_logged_in);

        mProfilePicture = (ImageView) findViewById(R.id.profile_image_image_view);
        mProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        mAddSessionsButton = (Button) findViewById(R.id.customer_profile_button_session_complete);
        mAddSessionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomerProfileActivity.this, MemberSignCompletedActivity.class);
                startActivity(intent);
            }
        });

        mSessionCompletedButton = (Button) findViewById(R.id.customer_profile_add_sessions_button);
        mSessionCompletedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomerProfileActivity.this, AddCustomerActivity.class);
                startActivity(intent);
            }
        });

        mToCustomerListButton = (Button) findViewById(R.id.go_to_customer_list_button);
        mToCustomerListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomerProfileActivity.this, CustomerListActivity.class);
                startActivity(intent);
            }
        });



        updateSelectedCustomerUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mProfilePicture.setImageBitmap(imageBitmap);




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

                            Intent intent = new Intent(CustomerProfileActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });

            alertDialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);

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
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void updateSelectedCustomerUI() {
        mSeletedCustomerNameTV.setText(mSelectedCustomer.getName());
        mNumberOfSessionsRemainingTV.setText(mSelectedCustomer.getSessionsRemaining().toString());
    }


}
