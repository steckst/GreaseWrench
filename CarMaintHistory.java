package bonacsoftware.com.greasewrench;

/**
 * Created by steckst on 2/17/2018.
 */

public class CarMaintHistory {
    public CarMaintHistory(){};
    public CarMaintHistory(String MaintType, String MaintMileage,
                           String MaintDate, String MaintPrice,
                           String MaintComments, long CarID){
        this.setMaintenanceType(MaintType);
        this.setMaintenanceMileage(MaintMileage);
        this.setCarMaintenanceDate(MaintDate);
        this.setMaintenancePrice(MaintPrice);
        this.setCarMaintenanceComments(MaintComments);
        this.setCarMaintenanceID(CarID);
    }


    // Car Maintenance Type - identifies the type of maintenance performed
    public String carMaintenanceType;
    public String getMaintenanceType(){
        return carMaintenanceType;
    }
    public void setMaintenanceType(String Type){
        this.carMaintenanceType = Type;
    }

    // Car Maintenance Mileage - mileage of car maintenance was performed
    public String carMaintenanceMileage;
    public String getMaintenanceMileage(){
        return carMaintenanceMileage;
    }
    public void setMaintenanceMileage (String Mileage){
        this.carMaintenanceMileage = Mileage;
    }

    // Car Maintenance Date - Date car maintenance was performed
    public String carMaintenanceDate;
    public String getMaintenanceDate(){
        return carMaintenanceDate;
    }
    public void setCarMaintenanceDate (String Date){
        this.carMaintenanceDate = Date;
    }

    // Car Maintenance Price - Price of car maintenance  performed
    public String carMaintenancePrice;
    public String getMaintenancePrice(){
        return carMaintenancePrice;
    }
    public void setMaintenancePrice (String Price){
        this.carMaintenancePrice = Price;
    }

    // Car Maintenance Comments - Additional comments about the maintenance
    public String carMaintenanceComments;
    public String getMaintenanceComments(){
        return carMaintenanceComments;
    }
    public void setCarMaintenanceComments (String Comments){
        this.carMaintenanceComments = Comments;
    }

    // Car Maintenance ID - the DB generated unique ID for the car
    public long carMaintenanceID;
    public long getCarMaintenanceID(){
        return this.carMaintenanceID;
    }
    public void setCarMaintenanceID(long ID){
        this.carMaintenanceID = ID;
    }

}
