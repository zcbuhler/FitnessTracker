package com.z.buhler.fitnesstracker.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.z.buhler.fitnesstracker.database.CustomerDbSchema.CustomerTable;

/**
 * Created by zacharybuhler on 4/14/18.
 */

public class CustomerBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "customerBase.db";

    public CustomerBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CustomerTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                CustomerTable.Cols.UUID + ", " +
                CustomerTable.Cols.NAME + ", " +
                CustomerTable.Cols.ADDRESS + ", " +
                CustomerTable.Cols.CREDIT_CARD_NUMBER +
                CustomerTable.Cols.EMAIL + ", " +
                CustomerTable.Cols.SESSIONS_REMAINING + ", " +
                CustomerTable.Cols.EMAIL_RECEIPT + ", " +
                CustomerTable.Cols.PRINT_RECEIPT +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
