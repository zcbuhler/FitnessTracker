package com.z.buhler.fitnesstracker.database;

/**
 * Created by zacharybuhler on 4/14/18.
 */

public class CustomerDbSchema {

    public static final class CustomerTable {
        public static final String NAME = "customers";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String FULL_NAME = "fullName";
            public static final String ADDRESS = "address";
            public static final String CREDIT_CARD_NUMBER = "creditCardNumber";
            public static final String EMAIL = "email";
            public static final String SESSIONS_REMAINING = "sessionsRemaining";
            public static final String EMAIL_RECEIPT = "emailReceipt";
            public static final String PRINT_RECEIPT = "printReceipt";
        }
    }
}
