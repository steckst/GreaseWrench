package com.example.greasewrench;

/*
 * Class for holding/retrieving/setting data from the CarInformation DB table
 * */
 
public class CarInfo {
	
	public CarInfo(){};
	public CarInfo(String make, String model, String year, String mileage){
		this.setCarMake(make);
		this.setCarModel(model);
		this.setCarYear(year);
		this.setCarMileage(mileage);

	}
	// Car Make - The make of the car
	public String carMake;
	public String getCarMake(){
		return carMake;
	}
	public void setCarMake(String Make){
		this.carMake = Make;
	}

	// Car Model - the model of the car
	public String carModel;
	public String getCarModel(){
		return carModel;
	}
	public void setCarModel(String Model){
		this.carModel = Model;
	}
	
	// Car Year - The year the car was manufactured
	public String carYear;
	public String getCarYear(){
		return carYear;
	}
	public void setCarYear(String Year){
		this.carYear = Year;
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
	public long carID;
	public void setCarID(long ID){
		this.carID = ID;
	}
	public long getCarID(){
		return this.carID;
	}

}
