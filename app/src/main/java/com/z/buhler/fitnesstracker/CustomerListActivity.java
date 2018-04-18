package com.z.buhler.fitnesstracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.z.buhler.fitnesstracker.database.CustomerBaseHelper;
import com.z.buhler.fitnesstracker.database.CustomerCursorWrapper;
import com.z.buhler.fitnesstracker.database.CustomerDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CustomerListActivity extends AppCompatActivity {

    private Button mViewProfileButton;
    private Button mAddCustomerButton;
    private TextView mLoginStatusFragTV;
    private RecyclerView mCustomerRecyclerView;
    private CustomerLab mCustomerLab;


    private ListView mCustomerListView;

    private TextView mTextViewOfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        mCustomerListView = findViewById(R.id.customer_list_customer);

        updateUI();
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
    public void onResume(){
        super.onResume();
        updateUI();
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



    private CustomerCursorWrapper queryCustomers(String whereClause, String[] whereArgs) {
        SQLiteDatabase mDatabase = new CustomerBaseHelper(this ).getReadableDatabase();
        Cursor cursor = mDatabase.query(
                CustomerDbSchema.CustomerTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new CustomerCursorWrapper(cursor);
    }

    public List<Customer>getCustomerList(){

        List<Customer> customers =  new ArrayList<>();

        CustomerCursorWrapper cursor = queryCustomers(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                customers.add(cursor.getCustomer());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        int size = customers.size();
        Log.d("NUMBER DATABASE IS: ", "" + customers.size());
        return customers;
    }

    private void updateUI() {


        List<Customer> customers = getCustomerList();

        ArrayAdapter<Customer> theCustomerArrayAdaptor = new ArrayAdapter<Customer>(
                this, R.layout.list_item_customer, R.id.customer_name_text, customers);

        mCustomerListView.setAdapter(theCustomerArrayAdaptor);

    }

}
