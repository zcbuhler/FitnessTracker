package com.z.buhler.fitnesstracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private Button mViewProfileButton;
    private Button mAddCustomerButton;
    private TextView mLoginStatusFragTV;
    private RecyclerView mCustomerRecyclerView;

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

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                        Bundle savedInstanceState){
//        View view = inflater.inflate(R.layout.activity_customer_list, container, false);
//
//        mCustomerRecyclerView = (RecyclerView) view
//                .findViewById(R.id.customer_list_customer);
//
//
//
//
//
//
//        return view;
//    }

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

    private class CustomerHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Customer mCustomer;
        private TextView mNameTextView;


        public CustomerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_customer, parent, false));
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.customer_name_text);

        }

        public void bind(Customer customer) {
            mCustomer = customer;
            mNameTextView.setText(mCustomer.getName());
        }

        @Override
        public void onClick(View view) {

            // !!! Update later to pass customer object info to populate the customer profile
            Intent intent = new Intent(CustomerListActivity.this, CustomerProfileActivity.class);
            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CustomerHolder> {

        private List<Customer> mCustomers;

        public void CustomerAdapter(List<Customer> customers) {
            mCustomers = customers;
        }

        @Override
        public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            return new CustomerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CustomerHolder holder, int position) {
            Customer customer = mCustomers.get(position);
            holder.bind(customer);
        }

       @Override
       public int getItemCount() {
           return mCustomers.size();
       }

        public void setCustomers(List<Customer> customers) {
            mCustomers = customers; }
    }

}
