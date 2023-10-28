package com.example.carhire;

public class Util {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hireDB";

    public static final String CLIENTS_TABLE_NAME = "clients";
    public static final String CARS_TABLE_NAME = "cars";
    public static final String TRANSACTIONS_TABLE_NAME = "transactions";

    public static final String CLIENT_ID = "id";
    public static final String CLIENT_NAME = "name";
    public static final String CLIENT_SURNAME = "surname";
    public static final String CLIENT_PHONE = "phone";
    public static final String CLIENT_TRANSACTIONS_NUM = "transactionsNum";

    public static final String CAR_ID = "id";
    public static final String CAR_BRAND = "brand";
    public static final String TRANSACTION_HIRE_COST = "hireCost";
    public static final String CAR_INSURANCE_COST = "insuranceCost";
    public static final String CAR_DAY_COST = "dayCost";

    public static final String TRANSACTION_ID = "id";
    public static final String TRANSACTION_CLIENT = "clientId";
    public static final String TRANSACTION_CAR = "carId";
    public static final String TRANSACTION_HIRE_DATE = "date";
    public static final String TRANSACTION_HIRE_DAYS = "days";
    public static int changeI = 0;

}