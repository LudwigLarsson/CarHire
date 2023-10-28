package com.example.carhire;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<Transaction> transactions;
    private Context context;
    private int counter = 0;

    public RecyclerViewAdapter(Context context, ArrayList<Transaction> transactions) {
        this.inflater = LayoutInflater.from(context);
        this.transactions = transactions;
        this.context = context;
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        private TextView value;
        private TextView name;
        private TextView surname;
        private TextView brand;
        private TextView date;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.value);
            name = itemView.findViewById(R.id.name);
            surname = itemView.findViewById(R.id.surname);
            brand = itemView.findViewById(R.id.brand);
            date = itemView.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataBaseHandler bd = new DataBaseHandler(context.getApplicationContext());
        Transaction transaction = this.transactions.get(position);
        Log.d("bindtransaction", String.valueOf(transaction.getClient()));
        Client client = bd.getClient(transaction.getClient());
        Log.d("client", client.toString());
        Car car = bd.getCar(transaction.getCar());
        ((MyHolder) holder).value.setText(String.valueOf(transaction.getId()));
        ((MyHolder) holder).name.setText(String.valueOf(client.getClientName()));
        ((MyHolder) holder).surname.setText(String.valueOf(client.getClientSurname()));
        ((MyHolder) holder).brand.setText(String.valueOf(car.getCarBrand()));
        ((MyHolder) holder).date.setText(String.valueOf(transaction.getHireDate()));
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.changeI = transaction.getId();
                Intent intent=new Intent(context, EditActivity.class);
                context.startActivity(intent);
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return transactions.size();
    }
}
