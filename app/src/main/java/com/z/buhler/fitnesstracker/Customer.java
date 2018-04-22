package com.z.buhler.fitnesstracker;

import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by zacharybuhler on 4/14/18.
 */

public class Customer implements Serializable{

    private UUID mId;
    private String mName;
    private String mAddress;
    private String mCreditCardNumber;
    private int mSessionsRemaining;
    private String mEmail;
    private Boolean mEmailReceipt;
    private Boolean mPrintReceipt;

    public Customer(){
        this(UUID.randomUUID());
    }

    public Customer(UUID id) {
        mId = id;
    }


    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCreditCardNumber() {
        return mCreditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        mCreditCardNumber = creditCardNumber;
    }

    public Integer getSessionsRemaining() {
        return mSessionsRemaining;
    }

    public void setSessionsRemaining(Integer sessionsRemaining) {
        mSessionsRemaining = sessionsRemaining;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Boolean getEmailReceipt() {
        return mEmailReceipt;
    }

    public void setEmailReceipt(Boolean emailReceipt) {
        mEmailReceipt = emailReceipt;
    }

    public Boolean getPrintReceipt() {
        return mPrintReceipt;
    }

    public void setPrintReceipt(Boolean printReciept) {
        mPrintReceipt = printReciept;
    }

    @Override
    public String toString() {

        String name = getName();
        return name;
    }
}
