package com.example.carhire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewActivity extends AppCompatActivity {

    EditText clientName;
    EditText clientSurname;
    EditText phone;
    EditText days;
    Spinner spinner;

    TextView dateText;

    final Calendar myCalendar= Calendar.getInstance();

    AppCompatButton button;
    AppCompatButton save;
    DataBaseHandler bd;
    SharedPreferences transactionsNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        clientName = findViewById(R.id.clientName);
        clientSurname = findViewById(R.id.clientSurname);
        phone = findViewById(R.id.phone);
        days = findViewById(R.id.days);
        spinner = findViewById(R.id.spinner);
        dateText = findViewById(R.id.date_text);
        button = findViewById(R.id.date);
        save = findViewById(R.id.save);
        transactionsNum = getSharedPreferences("num", Context.MODE_PRIVATE);
        bd = new DataBaseHandler(this);

        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        dateText.setText(dateFormat.format(myCalendar.getInstance().getTime()).toString());

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
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
                new DatePickerDialog(NewActivity.this, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
                Client client = new Client(resClientName, resClientSurname, resPhone, transactionsNum.getInt("num", 1));
                bd.addClient(client);
                Transaction transaction = new Transaction(transactionsNum.getInt("num", 1), resSpinner, resDateText, resDays, hireCost);
                bd.addTransaction(transaction);
                SharedPreferences.Editor editor = transactionsNum.edit();
                editor.putInt("num", transactionsNum.getInt("num", 1) + 1);
                editor.apply();

                Intent intent = new Intent(NewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}