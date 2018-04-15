package com.z.buhler.fitnesstracker.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.z.buhler.fitnesstracker.Customer;
import com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable;

import java.util.Date;
import java.util.UUID;

import static com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable.*;

/**
 * Created by zacharybuhler on 4/14/18.
 */

public class CustomerCursorWrapper extends CursorWrapper {

    public CustomerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Customer getCustomer() {
        String uuidString = getString(getColumnIndex(CustomerTable.Cols.UUID));
        String name = getString(getColumnIndex(CustomerTable.Cols.NAME));
        String address = getString(getColumnIndex(CustomerTable.Cols.ADDRESS));
        String creditCardNumber = getString(getColumnIndex(Cols.CREDIT_CARD_NUMBER));
        String email = getString(getColumnIndex(CustomerTable.Cols.EMAIL));
        int sessionRemaining = getInt(getColumnIndex(Cols.SESSIONS_REMAINING));
        int emailReceipt = getInt(getColumnIndex(Cols.EMAIL_RECEIPT));
        int printReceipt = getInt(getColumnIndex(Cols.PRINT_RECEIPT));

        Customer customer = new Customer(UUID.fromString(uuidString));
        customer.setName(name);
        customer.setAddress(address);
        customer.setCreditCardNumber(creditCardNumber);
        customer.setEmail(email);
        customer.setSessionsRemaining(sessionRemaining);
        customer.setEmailReceipt(emailReceipt == 1);
        customer.setPrintReciept(printReceipt == 1);

        return customer;
    }
}


