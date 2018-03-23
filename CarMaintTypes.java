package bonacsoftware.com.greasewrench;

/**
 * Created by steckst on 2/17/2018.
 */

public class CarMaintTypes {
    // Maintenance Type - Identifies a maintenance type
    public String MaintenanceType;

    // Constructors
    public CarMaintTypes (String type){
        setMaintenanceType (type);
    }

    public CarMaintTypes (){
        setMaintenanceType ("");
    }

    public String getMaintenanceType (){
        return this.MaintenanceType;
    }

    public void setMaintenanceType (String Type){
        this.MaintenanceType = Type;
    }

    // Maintenance Type - the DB generated unique ID for the maintenance type
    public Long ID;
    public Long getMaintenaceTypeID (){
        return this.ID;
    }
    /*
    private void setMaintenanceTypeID (Integer ID){
        this.ID = ID;
    }*/
    public void setMaintenanceID (Long id){
        this.ID = id;
    }


}
