package com.example.carhire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CLIENTS_TABLE = "CREATE TABLE " + Util.CLIENTS_TABLE_NAME + " ("
                + Util.CLIENT_ID + " INTEGER PRIMARY KEY, "
                + Util.CLIENT_NAME + " TEXT, "
                + Util.CLIENT_SURNAME + " TEXT, "
                + Util.CLIENT_PHONE + " TEXT, "
                + Util.CLIENT_TRANSACTIONS_NUM + " INTEGER" + " );";

        String CREATE_CARS_TABLE = "CREATE TABLE " + Util.CARS_TABLE_NAME + " ("
                + Util.CAR_ID + " INTEGER PRIMARY KEY, "
                + Util.CAR_BRAND + " TEXT, "
                + Util.CAR_INSURANCE_COST + " INTEGER, "
                + Util.CAR_DAY_COST + " INTEGER" + " );";

        String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE " + Util.TRANSACTIONS_TABLE_NAME + " ("
                + Util.TRANSACTION_ID + " INTEGER PRIMARY KEY, "
                + Util.TRANSACTION_CLIENT + " INTEGER, "
                + Util.TRANSACTION_CAR + " INTEGER, "
                + Util.TRANSACTION_HIRE_DATE + " TEXT, "
                + Util.TRANSACTION_HIRE_DAYS + " INTEGER, "
                + Util.TRANSACTION_HIRE_COST + " INTEGER" + " );";

        sqLiteDatabase.execSQL(CREATE_CLIENTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_CARS_TABLE);
        sqLiteDatabase.execSQL(CREATE_TRANSACTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.CLIENTS_TABLE_NAME + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.CARS_TABLE_NAME + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TRANSACTIONS_TABLE_NAME + ";");
        onCreate(sqLiteDatabase);
    }

    public void deleteAll(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        switch (tableName) {
            case "clients":
                db.delete(Util.CLIENTS_TABLE_NAME, null, null);
                break;
            case "cars":
                db.delete(Util.CARS_TABLE_NAME, null, null);
                break;
            case "transactions":
                db.delete(Util.TRANSACTIONS_TABLE_NAME, null, null);
                break;
        }
        //db.close();
    }

    public void addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues carContentValues = new ContentValues();
        carContentValues.put(Util.CAR_BRAND, car.getCarBrand());
        carContentValues.put(Util.CAR_INSURANCE_COST, car.getCarInsuranceCost());
        carContentValues.put(Util.CAR_DAY_COST, car.getCarDayCost());
        db.insert(Util.CARS_TABLE_NAME, null, carContentValues);
        //db.close();
    }
    public void addClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues clientContentValues = new ContentValues();
        clientContentValues.put(Util.CLIENT_NAME, client.getClientName());
        clientContentValues.put(Util.CLIENT_SURNAME, client.getClientSurname());
        clientContentValues.put(Util.CLIENT_PHONE, client.getClientPhone());
        clientContentValues.put(Util.CLIENT_TRANSACTIONS_NUM, client.getClientTransactionNum());
        db.insert(Util.CLIENTS_TABLE_NAME, null, clientContentValues);
        //db.close();
    }
    public void addTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues transactionContentValues = new ContentValues();
        transactionContentValues.put(Util.TRANSACTION_CLIENT, transaction.getClient());
        transactionContentValues.put(Util.TRANSACTION_CAR, transaction.getCar());
        transactionContentValues.put(Util.TRANSACTION_HIRE_DATE, transaction.getHireDate());
        transactionContentValues.put(Util.TRANSACTION_HIRE_DAYS, transaction.getHireDays());
        transactionContentValues.put(Util.TRANSACTION_HIRE_COST, transaction.getHireCost());
        db.insert(Util.TRANSACTIONS_TABLE_NAME, null, transactionContentValues);
        //db.close();
    }
    public Transaction getTransaction(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TRANSACTIONS_TABLE_NAME, new String[]{Util.TRANSACTION_ID, Util.TRANSACTION_CLIENT, Util.TRANSACTION_CAR, Util.TRANSACTION_HIRE_DATE, Util.TRANSACTION_HIRE_DAYS, Util.TRANSACTION_HIRE_COST},
                Util.TRANSACTION_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            Transaction transaction = new Transaction(Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
            cursor.close();
            return transaction;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public Client getClient(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.CLIENTS_TABLE_NAME, new String[]{Util.CLIENT_ID, Util.CLIENT_NAME, Util.CLIENT_SURNAME, Util.CLIENT_PHONE, Util.CLIENT_TRANSACTIONS_NUM},
                Util.CLIENT_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            Client client = new Client(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
            cursor.close();
            return client;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
    public Car getCar(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("carid", String.valueOf(id));
        Cursor cursor = db.query(Util.CARS_TABLE_NAME, new String[]{Util.CAR_ID, Util.CAR_BRAND, Util.CAR_INSURANCE_COST, Util.CAR_DAY_COST},
                Util.CAR_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            Car car = new Car(cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
            cursor.close();
            return car;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
    public ArrayList<Transaction> getAllTransactions() throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Transaction> transactionsList = new ArrayList<>();
        String selectAll = "Select * from " + Util.TRANSACTIONS_TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {

            do {
                Transaction transaction = new Transaction();
                transaction.setId(Integer.parseInt(cursor.getString(0)));
                transaction.setClient(Integer.parseInt(cursor.getString(1)));
                transaction.setCar(Integer.parseInt(cursor.getString(2)));
                transaction.setHireDays(Integer.parseInt(cursor.getString(4)));
                transaction.setHireDate(cursor.getString(3));

                transactionsList.add(transaction);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("transactions", transactionsList.toString());
        return transactionsList;
    }
    public int updateClient(Client client, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.CLIENT_NAME, client.getClientName());
        contentValues.put(Util.CLIENT_SURNAME, client.getClientSurname());
        contentValues.put(Util.CLIENT_PHONE, client.getClientPhone());

        return db.update(Util.CLIENTS_TABLE_NAME, contentValues, Util.CLIENT_ID + "=?", new String[]{String.valueOf(id)});
    }
    public int updateCar(Car car, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.CAR_BRAND, car.getCarBrand());
        contentValues.put(Util.CAR_INSURANCE_COST, car.getCarInsuranceCost());
        contentValues.put(Util.CAR_DAY_COST, car.getCarDayCost());
        return db.update(Util.CARS_TABLE_NAME, contentValues, Util.CAR_ID + "=?", new String[]{String.valueOf(id)});
    }
    public int updateTransaction(Transaction transaction, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.TRANSACTION_CLIENT, transaction.getClient());
        contentValues.put(Util.TRANSACTION_CAR, transaction.getCar());
        contentValues.put(Util.TRANSACTION_HIRE_DATE, transaction.getHireDate());
        contentValues.put(Util.TRANSACTION_HIRE_DAYS, transaction.getHireDays());
        contentValues.put(Util.TRANSACTION_HIRE_COST, transaction.getHireCost());

        return db.update(Util.TRANSACTIONS_TABLE_NAME, contentValues, Util.TRANSACTION_ID + "=?", new String[]{String.valueOf(id)});
    }
    public void deleteTransaction(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TRANSACTIONS_TABLE_NAME, Util.TRANSACTION_ID + "=?", new String[]{String.valueOf(id)});
        //db.close();
    }
    public void deleteCar(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.CARS_TABLE_NAME, Util.CAR_ID + "=?", new String[]{String.valueOf(id)});
        //db.close();
    }
    public void deleteClient(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.CLIENTS_TABLE_NAME, Util.CLIENT_ID + "=?", new String[]{String.valueOf(id)});
        //db.close();
    }
}
