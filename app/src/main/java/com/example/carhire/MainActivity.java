package com.example.carhire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter adapter;
    DataBaseHandler bd;

    FloatingActionButton fab;

    SharedPreferences transactionsNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new DataBaseHandler(this);
        ArrayList<Transaction> transactions = null;
        try {
            transactions = (ArrayList<Transaction>) bd.getAllTransactions();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        transactionsNum = getSharedPreferences("num", Context.MODE_PRIVATE);
        if (transactionsNum.getInt("num", 1) == 1) {
            initCarsInCarPark();
        } else {
            try {
                transactions = (ArrayList<Transaction>) bd.getAllTransactions();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            //adapter.notifyDataSetChanged();
        }
        adapter = new RecyclerViewAdapter(this, transactions);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Log.d("adapter", "adapter");
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });
    }
    public void initCarsInCarPark() {
        Car car1 = new Car("Москвич 3", 1, 10);
        Car car2 = new Car("Lada Vesta", 2, 15);
        Car car3 = new Car("Nissan Skyline r34", 3, 20);
        bd.addCar(car1);
        bd.addCar(car2);
        bd.addCar(car3);
    }
}