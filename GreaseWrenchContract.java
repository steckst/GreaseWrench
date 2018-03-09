package bonacsoftware.com.greasewrench;

import android.provider.BaseColumns;

/**
 * Created by steckst on 2/17/2018.
 */
// to prevent someone from accidentally instantiating the this class,
// make the constructor private.

public class GreaseWrenchContract {

    private GreaseWrenchContract() {
    }

    public static final String COLUMN_ID = "_id";

    //Constants for Car Information DB table
    public static class CarInformation implements BaseColumns {
        public static final String TABLE_NAME = "car_information";
        public static final String COLUMN_CAR_MAKE = "car_make";
        public static final String COLUMN_CAR_MODEL = "car_model";
        public static final String COLUMN_CAR_YEAR = "car_year";
        public static final String COLUMN_CAR_MILEAGE = "car_mileage";
        public static final String COLUMN_CAR_IMAGE = "car_image";

    }

    //Constants for Car maintenance type DB table
    public static class CarMaintenanceTypes implements BaseColumns {
        public static final String TABLE_NAME = "car_maintenance_types";
        public static final String COLUMN_MAINTENANCE_TYPE = "type";

    }

    //Constants for Car Maintenance History DB table
    public static class CarMaintenanceHistory implements BaseColumns {
        public static final String TABLE_NAME = "car_information_history";
        public static final String COLUMN_MAINTENANCE_CAR_ID = "car_id";
        public static final String COLUMN_MAINTENANCE_TYPE = "maintenance_type";
        public static final String COLUMN_MILEAGE = "car_mileage";
        public static final String COLUMN_MAINTENANCE_DATE = "maintenance_date";
        public static final String COLUMN_MAINTENANCE_PRICE = "maintenance_price";
        public static final String COLUMN_MAINTENANCE_COMMENTS = "comments";
    }

//SQL command to createCar Maintenance History DB table


    public static class CarFuelHistory implements BaseColumns {
        public static final String TABLE_NAME = "car_fuel_history";
        public static final String COLUMN_CAR_MILEAGE = "car_mileage";
        public static final String COLUMN_CAR_GALLONS = "car_gallons";
    }

    //***************************Car Information Table*************************
    //SQL command to create Car Information DB table
    static final String SQL_CREATE_CAR_INFORMATION_TABLE =
            "CREATE TABLE " + CarInformation.TABLE_NAME + " (" +
                    CarInformation._ID + " INTEGER PRIMARY KEY," +
                    CarInformation.COLUMN_CAR_MAKE + " TEXT," +
                    CarInformation.COLUMN_CAR_MODEL + " TEXT," +
                    CarInformation.COLUMN_CAR_YEAR + " TEXT," +
                    CarInformation.COLUMN_CAR_MILEAGE + " TEXT," +
                    CarInformation.COLUMN_CAR_IMAGE + " TEXT)";

    // SQL command to delete Car Information Table
    static final String SQL_DELETE_CAR_INFORMATION_TABLE =
            "DROP TABLE IF EXISTS " + CarInformation.TABLE_NAME;

    // SQL command to delete all rows in the Car Information table
    static final String SQL_DELETE_ROWS_FROM_CAR_INFO_TABLE =
            "DELETE FROM " + CarInformation.TABLE_NAME;


    //***************************Car Maintenance History Table*************************
    //SQL command to create Car Maintenance History DB table
    static final String SQL_CREATE_CAR_MAINTENANCE_HISTORY_TABLE =
            "CREATE TABLE " + CarMaintenanceHistory.TABLE_NAME + " (" +
                    CarMaintenanceHistory._ID + " INTEGER PRIMARY KEY," +
                    CarMaintenanceHistory.COLUMN_MAINTENANCE_CAR_ID + " TEXT," +
                    CarMaintenanceHistory.COLUMN_MAINTENANCE_TYPE + " TEXT," +
                    CarMaintenanceHistory.COLUMN_MILEAGE + " TEXT," +
                    CarMaintenanceHistory.COLUMN_MAINTENANCE_DATE + " TEXT," +
                    CarMaintenanceHistory.COLUMN_MAINTENANCE_PRICE + " TEXT," +
                    CarMaintenanceHistory.COLUMN_MAINTENANCE_COMMENTS + " TEXT)";

    // SQL command to delete Car Maintenance History table
    static final String SQL_DELETE_CAR_MAINTENANCE_HISTORY_TABLE =
            "DROP TABLE IF EXISTS " + CarMaintenanceHistory.TABLE_NAME;

    static final String SQL_DELETE_CAR_MAINTENANCE_HISTORY_ROWS =
            "DELETE FROM " + CarMaintenanceHistory.TABLE_NAME;

    //***************************Car Maintenance Types Table*************************
    //SQL command to create Car maintenance type DB table
    static final String SQL_CREATE_CAR_MAINTENANCE_TYPES_TABLE =
            "CREATE TABLE " + CarMaintenanceTypes.TABLE_NAME + " (" +
                    CarMaintenanceTypes._ID + " INTEGER PRIMARY KEY," +
                    CarMaintenanceTypes.COLUMN_MAINTENANCE_TYPE + " TEXT)";

    // SQL command to delete Car Maintenance Type table
    static final String SQL_DELETE_CAR_MAINTENANCE_TYPES_TABLE =
            "DROP TABLE IF EXISTS " + CarMaintenanceTypes.TABLE_NAME;

    // SQL command to delete all rows from the Car Maintenance Types table
    static final String SQL_DELETE_ROWS_FROM_CAR_MAINTENANCE_TYPES_TABLE =
            "DELETE FROM " + CarMaintenanceTypes.TABLE_NAME;

    //***************************Car Fuel History Table*************************

    //SQL command to create Car fuel history DB table
    static final String SQL_CREATE_CAR_FUEL_HISTORY_TABLE =
            "CREATE TABLE " + CarFuelHistory.TABLE_NAME + " (" +
                    CarFuelHistory._ID + " INTEGER PRIMARY KEY," +
                    CarFuelHistory.COLUMN_CAR_GALLONS + " TEXT," +
                    CarFuelHistory.COLUMN_CAR_MILEAGE + " TEXT)";

    // SQL command to delete Car Fuel History table
    static final String SQL_DELETE_CAR_FUEL_HISTORY_TABLE =
            "DROP TABLE IF EXISTS " + CarFuelHistory.TABLE_NAME;


}
