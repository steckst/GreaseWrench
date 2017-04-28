package com.example.greasewrench;

/**
 * Created by steckst on 4/21/2017.
 */

public class CarFuelHist {

    // Car Make - The make of the car
    public Integer carGallons;
    public Integer getCarGallons(){
        return carGallons;
    }
    public void setCarGallons(Integer gallons){
        this.carGallons = gallons;
    }

    // Car Mileage - current mileage of the car
    public String carMileage;
    public String getCarMileage(){
        return this.carMileage;
    }
    public void setCarMileage(String Mileage){
        this.carMileage = Mileage;
    }

    // Car ID - the DB generated unique ID for the car
    public Integer carID;
    public void setCarID(Integer ID){
        this.carID = ID;
    }
    public Integer getCarID(){
        return this.carID;
    }

}
