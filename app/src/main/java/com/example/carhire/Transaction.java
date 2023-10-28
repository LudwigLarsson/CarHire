package com.example.carhire;

import java.util.Date;

public class Transaction {

    int id;
    private String hireDate;
    private int hireDays;
    int client;
    int car;
    int hireCost;

    public Transaction(int client, int car, String hireDate, int hireDays, int hireCost) {
        this.hireDate = hireDate;
        this.hireDays = hireDays;
        this.client = client;
        this.car = car;
        this.hireCost = hireCost;
    }
    public Transaction(int id, int client, int car, String hireDate, int hireDays, int hireCost) {
        this.id = id;
        this.hireDate = hireDate;
        this.hireDays = hireDays;
        this.client = client;
        this.car = car;
        this.hireCost = hireCost;
    }

    public int getHireCost() {
        return hireCost;
    }

    public void setHireCost(int hireCost) {
        this.hireCost = hireCost;
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHireDate() {
        return hireDate;
    }

    public int getCar() {
        return car;
    }

    public int getHireDays() {
        return hireDays;
    }

    public int getClient() {
        return client;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public void setHireDays(int hireDays) {
        this.hireDays = hireDays;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public void setClient(int client) {
        this.client = client;
    }
}
