package com.example.carhire;

import java.util.Date;

public class Car {
    private String carBrand;
    private int carInsuranceCost;
    private int carDayCost;

    public Car(String carBrand, int carInsuranceCost, int carDayCost) {
        this.carBrand = carBrand;
        this.carInsuranceCost = carInsuranceCost;
        this.carDayCost = carDayCost;
    }

    public int getCarDayCost() {
        return carDayCost;
    }

    public void setCarDayCost(int carDayCost) {
        this.carDayCost = carDayCost;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public int getCarInsuranceCost() {
        return carInsuranceCost;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setCarInsuranceCost(int carInsuranceCost) {
        this.carInsuranceCost = carInsuranceCost;
    }
}
