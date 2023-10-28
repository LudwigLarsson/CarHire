package com.example.carhire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {

    EditText clientName;
    EditText clientSurname;
    EditText phone;
    EditText days;
    Spinner spinner;

    TextView dateText;

    final Calendar myCalendar = Calendar.getInstance();

    AppCompatButton button;
    AppCompatButton save;

    AppCompatButton delete;
    DataBaseHandler bd;
    SharedPreferences transactionsNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        clientName = findViewById(R.id.clientName);
        clientSurname = findViewById(R.id.clientSurname);
        phone = findViewById(R.id.phone);
        days = findViewById(R.id.days);
        spinner = findViewById(R.id.spinner);
        dateText = findViewById(R.id.date_text);
        button = findViewById(R.id.date);
        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);
        bd = new DataBaseHandler(this);

        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        Bundle extras = getIntent().getExtras();
        int currentId = extras.getInt("id");

        Transaction transaction;
        try {
            transaction = bd.getTransaction(currentId);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Client client = bd.getClient(transaction.getClient());

        dateText.setText(transaction.getHireDate());
        Log.d("clientname", client.getClientName());
        clientName.setText(client.getClientName());
        clientSurname.setText(client.getClientSurname());
        phone.setText(client.getClientPhone());
        days.setText(String.valueOf(transaction.getHireDays()));

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);

                String myFormat="MM/dd/yy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                dateText.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EditActivity.this, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resClientName = String.valueOf(clientName.getText());
                String resClientSurname = String.valueOf(clientSurname.getText());
                String resPhone = String.valueOf(phone.getText());

                int resDays = Integer.parseInt(String.valueOf(days.getText()));
                String resDateText = dateFormat.format(myCalendar.getTime()).toString();
                int resSpinner = spinner.getSelectedItemPosition() + 1;
                Car car = bd.getCar(resSpinner);
                int hireCost = car.getCarDayCost() * resDays;

                Transaction newTransaction = new Transaction(0, transaction.getClient(), resSpinner, resDateText, resDays, hireCost);
                bd.updateTransaction(newTransaction, currentId);
                Client newClient = new Client(0, resClientName, resClientSurname, resPhone, client.getClientTransactionNum());
                bd.updateClient(newClient, transaction.getClient());
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.deleteTransaction(currentId);
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}