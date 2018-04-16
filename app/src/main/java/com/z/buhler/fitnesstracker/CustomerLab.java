package com.z.buhler.fitnesstracker;

import java.util.UUID;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.z.buhler.fitnesstracker.database.CustomerBaseHelper;
import com.z.buhler.fitnesstracker.database.CustomerCursorWrapper;
import com.z.buhler.fitnesstracker.database.CustomerDbSchema;

import java.util.ArrayList;
import java.util.List;

import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.*;

import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.ADDRESS;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.CREDIT_CARD_NUMBER;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.EMAIL;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.EMAIL_RECEIPT;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.FULL_NAME;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.PRINT_RECEIPT;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.SESSIONS_REMAINING;
import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.Cols.UUID;



/**
 * Created by zacharybuhler on 4/15/18.
 */

public class CustomerLab {
    private static CustomerLab sCustomerLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

//    public static CustomerLab get(Context context) {
//        if (sCustomerLab == null) {
//            sCustomerLab = new CustomerLab(context);
//        }
//
//        return sCustomerLab;
//    }
//
//    private CustomerLab(Context context) {
//        mContext = context.getApplicationContext();
//        mDatabase = new CustomerBaseHelper(mContext)
//                .getWritableDatabase();
//
//    }
//
//    public void addCustomer(Customer c) {
//        ContentValues values = getContentValues(c);
//        mDatabase.insert(CustomerDbSchema.CustomerTable.NAME, null, values);
//    }
//
//    public List<Customer> getCustomers() {
//        List<Customer> crimes = new ArrayList<>();
//        CustomerCursorWrapper cursor = queryCustomers(null, null);
//        try {
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                crimes.add(cursor.getCustomer());
//                cursor.moveToNext();
//            }
//        } finally {
//            cursor.close();
//        }
//        return crimes;
//    }
//
//    public Customer getCustomer(UUID id) {
//        CustomerCursorWrapper cursor = queryCustomers(
//                CustomerDbSchema.CustomerTable.Cols.UUID + " = ?",
//                new String[]{id.toString()}
//        );
//        try {
//            if (cursor.getCount() == 0) {
//                return null;
//            }
//            cursor.moveToFirst();
//            return cursor.getCustomer();
//        } finally {
//            cursor.close();
//        }
//    }
//
//    public void updateCustomer(Customer customer) {
//        String uuidString = customer.getId().toString();
//        ContentValues values = getContentValues(customer);
//        mDatabase.update(CustomerDbSchema.CustomerTable.NAME, values,
//                CustomerDbSchema.CustomerTable.Cols.UUID + " = ?",
//                new String[]{uuidString});
//    }
//
//    private CustomerCursorWrapper queryCustomers(String whereClause, String[] whereArgs) {
//        Cursor cursor = mDatabase.query(
//                CustomerDbSchema.CustomerTable.NAME,
//                null, // Columns - null selects all columns
//                whereClause,
//                whereArgs,
//                null, // groupBy
//                null, // having
//                null  // orderBy
//        );
//        return new CustomerCursorWrapper(cursor);
//    }
//
//    private static ContentValues getContentValues(Customer customer) {
//        ContentValues values = new ContentValues();
//        values.put(UUID, customer.getId().toString());
//        values.put(NAME, customer.getName());
//        values.put(ADDRESS, customer.getAddress());
//        values.put(CREDIT_CARD_NUMBER, customer.getCreditCardNumber());
//        values.put(EMAIL, customer.getEmail());
//        values.put(SESSIONS_REMAINING, customer.getSessionsRemaining());
//
//        values.put(PRINT_RECEIPT, customer.getPrintReceipt() ? 1 : 0);
//        values.put(EMAIL_RECEIPT, customer.getEmailReceipt() ? 1 : 0);
//        return values;
//    }


}
