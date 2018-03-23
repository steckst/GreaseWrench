package bonacsoftware.com.greasewrench;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static bonacsoftware.com.greasewrench.GreaseWrenchContract.*;


/**
 * Created by steckst on 2/17/2018.
 */


public class GreaseWrenchDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GreaseWrench.db";


    public GreaseWrenchDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        // Constants defined in com.example.greasewrench.GreaseWrenchContract
        //Create the database tables for this application
        db.execSQL(SQL_CREATE_CAR_INFORMATION_TABLE);
        db.execSQL(SQL_CREATE_CAR_MAINTENANCE_TYPES_TABLE);
        db.execSQL(SQL_CREATE_CAR_MAINTENANCE_HISTORY_TABLE);
        db.execSQL(SQL_CREATE_CAR_FUEL_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        // Delete all tables when version of the DB has changed
        // NOTE - All data will be lost unless it is saved
        db.execSQL(SQL_DELETE_CAR_INFORMATION_TABLE);
        db.execSQL(SQL_DELETE_CAR_MAINTENANCE_TYPES_TABLE);
        db.execSQL(SQL_DELETE_CAR_MAINTENANCE_HISTORY_TABLE);
        db.execSQL(SQL_DELETE_CAR_FUEL_HISTORY_TABLE);

        //re-create the database tables for this application
        db.execSQL(SQL_CREATE_CAR_INFORMATION_TABLE);
        db.execSQL(SQL_CREATE_CAR_MAINTENANCE_TYPES_TABLE);
        db.execSQL(SQL_CREATE_CAR_MAINTENANCE_HISTORY_TABLE);
        db.execSQL(SQL_CREATE_CAR_FUEL_HISTORY_TABLE);


    }

    /*
    * Method Name - rebuildDBUtility()
    * Input Parameters
    *		SQLiteDatabase dbHandle
    * Return Parameters
    *		none
    * Purpose:
    * 	Utility method to delete all tables and rebuild them.
    *   Allows one to re-initialize the DB whenever they want to.
    *   NOTE - All data will be lost
    *
    */
    public void rebuildDBUtility(SQLiteDatabase dbHandle) {

        try {
            if (dbHandle.isOpen()) {

                // Delete all tables when version of the DB has changed
                // NOTE - All data will be lost unless it is saved
                dbHandle.execSQL(SQL_DELETE_CAR_INFORMATION_TABLE);
                dbHandle.execSQL(SQL_DELETE_CAR_MAINTENANCE_TYPES_TABLE);
                dbHandle.execSQL(SQL_DELETE_CAR_MAINTENANCE_HISTORY_TABLE);
                dbHandle.execSQL(SQL_DELETE_CAR_FUEL_HISTORY_TABLE);

                //re-create the database tables for this application
                dbHandle.execSQL(SQL_CREATE_CAR_INFORMATION_TABLE);
                dbHandle.execSQL(SQL_CREATE_CAR_MAINTENANCE_TYPES_TABLE);
                dbHandle.execSQL(SQL_CREATE_CAR_MAINTENANCE_HISTORY_TABLE);
                dbHandle.execSQL(SQL_CREATE_CAR_FUEL_HISTORY_TABLE);
            }
        } catch (android.database.SQLException e) {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.rebuildDBUtility(): Exception occured rebuilding database tables " + e.getMessage());
        }
    }

    /*********
     * SQL Operations for retrieving, adding, updating, deleting and initial loading of
     * car information
     ********/
    /*
    * Method Name - addCarInformation()
	* Input Parameters
	*		CarInfo Info
	*		SQLiteDatabase dbHandle
	* Return Parameters
	*		none
	* Purpose:
 	*/
    public boolean addCarInformation(CarInfo Info, SQLiteDatabase dbHandle) {

        boolean retValue = true;
        ContentValues values = new ContentValues();

        if (dbHandle.isOpen()) {
            // Constants below are defined in GreaseWrenchContract.CarInformation.
            values.put(CarInformation.COLUMN_CAR_MAKE, Info.getCarMake());
            values.put(CarInformation.COLUMN_CAR_MODEL, Info.getCarModel());
            values.put(CarInformation.COLUMN_CAR_YEAR, Info.getCarYear());
            values.put(CarInformation.COLUMN_CAR_MILEAGE, Info.getCarMileage());
            values.put(CarInformation.COLUMN_CAR_IMAGE, " ");


            if (dbHandle.insert(GreaseWrenchContract.CarInformation.TABLE_NAME, null, values) == -1) {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.addCarInformation(): Error inserting Car Information record ");
                retValue = false;
            }
        }
        return retValue;

    }

    /*
    * Method Name - getCarInformation()
    * Input Parameters
    *		SQLiteDatabase dbHandle
    *		long ID
    * Return Parameters
    *		CarInfo class object - instanceance of the CarInfo class
    * Purpose:
     */
    public CarInfo getCarInformation(SQLiteDatabase dbHandle, long ID) {
        CarInfo carInfo = null;

        String[] allColumns = {COLUMN_ID, CarInformation.COLUMN_CAR_MAKE,
                CarInformation.COLUMN_CAR_MODEL, CarInformation.COLUMN_CAR_YEAR,
                CarInformation.COLUMN_CAR_MILEAGE};
        try {
            if (dbHandle.isOpen()) {
                Cursor cursor = dbHandle.query(CarInformation.TABLE_NAME, allColumns,
                        COLUMN_ID + "=?", new String[]{String.valueOf(ID)}, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        carInfo = cursorToCarInfo(cursor);
                        cursor.moveToNext();
                    }
                    cursor.close();
                } else {
                    carInfo = null;
                }
            } // if dbHandle.isOpen()
            else {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.getCarInformation(): Database is not open for read");
            }
        } //try
        catch (android.database.SQLException e) {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.getCarInformation(): Exception occured performing database query " + e.getMessage());
        }

        return (carInfo);
    }

    /*
    * Method Name - deleteCarInformation()
    * Input Parameters
    *		SQLiteDatabase dbHandle
    *		CarInfo Info
    * Return Parameters
    *		none
    * Purpose:
     */
    public void deleteCarInformation(SQLiteDatabase dbHandle, CarInfo Info) {

        try {
            if (dbHandle.isOpen()) {
                dbHandle.delete(CarInformation.TABLE_NAME,
                        COLUMN_ID + "=?",
                        new String[]{String.valueOf(Info.getCarID())});
            } // if dbHandle.isOpen()
            else {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.deleteCarInformation(): Database is not open for deleting a record");
            }
        } //try
        catch (android.database.SQLException e) {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.deleteCarInformation(): Exception occured performing database delete " + e.getMessage());
        }

    }

    /*
    * Method Name - updateCarInformation()
    * Input Parameters
    *		SQLiteDatabase dbHandle
    *		CarInfo Info
    * Return Parameters
    *		none
    * Purpose:
     */
    public void updateCarInformation(SQLiteDatabase dbHandle, CarInfo Info) {
        ContentValues values = new ContentValues();

        values.put(CarInformation.COLUMN_CAR_MAKE, Info.getCarMake());
        values.put(CarInformation.COLUMN_CAR_MODEL, Info.getCarModel());
        values.put(CarInformation.COLUMN_CAR_YEAR, Info.getCarYear());
        values.put(CarInformation.COLUMN_CAR_MILEAGE, Info.getCarMileage());

        try {
            if (dbHandle.isOpen()) {
                dbHandle.update(CarInformation.TABLE_NAME,
                        values,
                        COLUMN_ID + "=?",
                        new String[]{String.valueOf(Info.getCarID())});
            } // if dbHandle.isOpen()
            else {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.updateCarInformation(): Database is not open for updating a record");
            }
        } //try
        catch (android.database.SQLException e) {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.updateCarInformation(): Exception occured performing database update " + e.getMessage());
        }


    }

    /*
    * Method Name - getAllCarInfo()
    * Input Parameters
    *		SQLiteDatabase dbHandle - handle to the SQlite database
    * Return Parameters
    *		List<CarInfo> infoList - a list contain all the data from the Car Information table or null if no data was retrieved
    * Purpose:
    */
    public List<CarInfo> getAllCarInfo(SQLiteDatabase dbHandle) {
        List<CarInfo> infoList = new ArrayList<CarInfo>();

        String[] allColumns = {"_id", CarInformation.COLUMN_CAR_MAKE,
                CarInformation.COLUMN_CAR_MODEL, CarInformation.COLUMN_CAR_YEAR,
                CarInformation.COLUMN_CAR_MILEAGE};


        if (dbHandle.isOpen()) {
            try {
                Cursor cursor = dbHandle.query(CarInformation.TABLE_NAME, allColumns,
                        null, null, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        CarInfo carInfo = cursorToCarInfo(cursor);
                        infoList.add(carInfo);
                        cursor.moveToNext();
                    }
                } else
                    infoList = null;

                cursor.close();

            } catch (android.database.SQLException e) {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.getAllCarInfo(): Exception occured performing database query " + e.getMessage());
            }
        } else {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.getAllCarInfo(): Database is not open for retrieving all CarInfo records");
        }


        return infoList;
    }

    /*
    * Method Name - cursorToCarInfo()
    * Input Parameters
    * Return Parameters
    * Purpose:
     */
    private CarInfo cursorToCarInfo(Cursor cursor) {
        CarInfo info = new CarInfo();

        info.setCarID(cursor.getLong(0));
        info.setCarMake(cursor.getString(1));
        info.setCarModel(cursor.getString(2));
        info.setCarYear(cursor.getString(3));
        info.setCarMileage(cursor.getString(4));

        return info;
    }

    /*
    * Method Name - loadCarInfo()
    * Input Parameters
    * Return Parameters
    * Purpose:
    */
    public void loadCarInfo(SQLiteDatabase dbHandle) {
        List<CarInfo> carInfoList = new ArrayList<CarInfo>();

        if (dbHandle.isOpen()) {
            try {
                // Clear all rows from table
                dbHandle.execSQL(SQL_DELETE_ROWS_FROM_CAR_INFO_TABLE);

                carInfoList.add(createCarInfo("Honda", "CRV", "1997", "297000"));
                carInfoList.add(createCarInfo("Chevrolet", "Suburban", "2005", "156000"));
                carInfoList.add(createCarInfo("Hyundai", "Santa Fe", "2004", "167000"));
                carInfoList.add(createCarInfo("Ford", "Fusion", "2012", "32000"));
                carInfoList.add(createCarInfo("Toyota", "RAV4", "2018", "00000"));
                carInfoList.add(createCarInfo("Test", "Fusion", "2012", "32000"));
                carInfoList.add(createCarInfo("Test1", "Fusion", "2012", "32000"));
                carInfoList.add(createCarInfo("Test2", "Fusion", "2012", "32000"));
                carInfoList.add(createCarInfo("Test3", "Fusion", "2012", "32000"));
                carInfoList.add(createCarInfo("Test4", "Fusion", "2012", "32000"));
                carInfoList.add(createCarInfo("Test5", "Fusion", "2012", "32000"));
                carInfoList.add(createCarInfo("Test6", "Fusion", "2012", "32000"));
                carInfoList.add(createCarInfo("Test7", "Fusion", "2012", "32000"));

                for (CarInfo l : carInfoList) {
                    addCarInformation(l, dbHandle);
                }
            } catch (android.database.SQLException e) {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.loadCarInfo(): Exception occured performing database query " + e.getMessage());
            }
        } else {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.loadCarInfo(): Database is not open for loading all CarInfo records");
        }
    }

    /*
    * Method Name - createCarInfo()
    * Input Parameters
    * Return Parameters
    * Purpose:
    */
    CarInfo createCarInfo(String make, String model, String year, String mileage) {
        return (new CarInfo(make, model, year, mileage));

    }

    /*********
     * SQL Operations for retrieving, adding, updating, deleting and initial loading of
     * maintenance types
     ********/
	/*
	* Method Name - addMaintenanceType()
	* Input Parameters
	* Return Parameters
	* Purpose:
 	*/
    public void addMaintenanceType(CarMaintTypes Type, SQLiteDatabase dbHandle) {

        if (dbHandle.isOpen()) {
            try {
                ContentValues values = new ContentValues();
                // Constants below are defined in GreaseWrenchContract.CarInformation.
                values.put(CarMaintenanceTypes.COLUMN_MAINTENANCE_TYPE, Type.getMaintenanceType());

                dbHandle.insert(GreaseWrenchContract.CarMaintenanceTypes.TABLE_NAME, null, values);

            } catch (android.database.SQLException e) {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.addMaintenanceType(): Exception occured performing database query " + e.getMessage());
            }
        } else {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.addMaintenanceType(): Database is not open for loading all CarInfo records");
        }
    }

    /*
    * Method Name - deleteMaintenanceType()
    * Input Parameters
    *		SQLiteDatabase dbHandle
    *		CarMaintTypes Type
    * Return Parameters
    *		void
    * Purpose:
     */
    public void deleteMaintenanceType(SQLiteDatabase dbHandle, CarMaintTypes Type) {
        try {
            if (dbHandle.isOpen()) {
                dbHandle.delete(CarMaintenanceTypes.TABLE_NAME,
                        COLUMN_ID + "=?",
                        new String[]{Type.getMaintenanceType()});
            } // if dbHandle.isOpen()
            else {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.deleteMaintenanceType(): Database is not open for deleting a record");
            }
        } //try
        catch (android.database.SQLException e) {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.deleteMaintenanceType(): Exception occured performing database delete " + e.getMessage());
        }

    }

    /*
    * Method Name - deleteMaintenanceType()
    * Input Parameters
    *		SQLiteDatabase dbHandle
    *		CarMaintTypes Type
    * Return Parameters
    *		void
    * Purpose:
     */
    public void updateMaintenenaceType(SQLiteDatabase dbHandle, CarMaintTypes Type) {
        ContentValues values = new ContentValues();

        values.put(CarMaintenanceTypes.COLUMN_MAINTENANCE_TYPE, Type.getMaintenanceType());

        try {
            if (dbHandle.isOpen()) {
                dbHandle.update(CarMaintenanceTypes.TABLE_NAME,
                        values,
                        COLUMN_ID + "=?",
                        new String[]{String.valueOf(Type.getMaintenaceTypeID())});
            } // if dbHandle.isOpen()
            else {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.updateMaintenenaceType(): Database is not open for updating a record");
            }
        } //try
        catch (android.database.SQLException e) {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.updateMaintenenaceType(): Exception occured performing database update " + e.getMessage());
        }

    }

    /*
    * Method Name - getMaintenanceType()
    * Input Parameters
    *		SQLiteDatabase dbHandle
    *		long ID
    * Return Parameters
    *		void
    * Purpose:
     */
    public CarMaintTypes getMaintenanceType(SQLiteDatabase dbHandle, long ID) {
        CarMaintTypes Type = null;


        String[] allColumns = {COLUMN_ID,
                CarMaintenanceTypes.COLUMN_MAINTENANCE_TYPE};

        if (dbHandle.isOpen()) {
            try {
                Cursor cursor = dbHandle.query(CarMaintenanceTypes.TABLE_NAME, allColumns,
                        COLUMN_ID + "=?", new String[]{String.valueOf(ID)}, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        Type = cursorToMaintType(cursor);
                        ;
                        cursor.moveToNext();
                    }
                    cursor.close();
                } else {
                    Type = null;
                }
            } //try
            catch (android.database.SQLException e) {
                Log.d(android.content.ContentValues.TAG, "getMaintenanceType.getMaintenanceType(): Exception occured performing database query " + e.getMessage());
            }
        } else {
            Log.d(android.content.ContentValues.TAG, "getMaintenanceType.getMaintenanceType(): Database is not open for read");
        }


        return (Type);
    }

    /*
    * Method Name - getAllMainteanceTypes()
    * Input Parameters
    *		SQLiteDatabase dbHandle - handle to the SQlite database
    * Return Parameters
    *		List<CarMaintTypes> maintTypes - a list contain all the data from the Car Maintenance Type table or null if no data was retrieved
    * Purpose:
    *		Retrieve all the data from the Car Maintenance Type table
     */
    public List<CarMaintTypes> getAllMainteanceTypes(SQLiteDatabase dbHandle) {
        List<CarMaintTypes> maintTypes = new ArrayList<CarMaintTypes>();
        // Columns to be reireved from the Car Maintenance Type table
        String[] allColumns = {"_id",
                CarMaintenanceTypes.COLUMN_MAINTENANCE_TYPE};

        if (dbHandle.isOpen()) {
            try {

                // Query the table
                Cursor cursor = dbHandle.query(CarMaintenanceTypes.TABLE_NAME, allColumns,
                        null, null, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        CarMaintTypes maintType = cursorToMaintType(cursor);
                        maintTypes.add(maintType);
                        cursor.moveToNext();
                    }
                } else
                    maintTypes = null;

                cursor.close();
            } catch (android.database.SQLException e) {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.getAllMainteanceTypes(): Exception occured performing database query " + e.getMessage());
            }
        } else {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.getAllMainteanceTypes(): Database is not open for loading all CarInfo records");
        }
        return maintTypes;
    }

    /*
    * Method Name - cursorToMaintType()
    * Input Parameters
    * Return Parameters
    * Purpose:
     */
    private CarMaintTypes cursorToMaintType(Cursor cursor) {
        CarMaintTypes maintType = new CarMaintTypes();
        maintType.setMaintenanceType(cursor.getString(1));
        maintType.setMaintenanceID(cursor.getLong(0));
        return maintType;
    }

    /*
    * Method Name - loadMaintenanceTypes()
    * Input Parameters
    * Return Parameters
    * Purpose:
     */
    public void loadMaintenanceTypes(SQLiteDatabase dbHandle) {
        List<CarMaintTypes> maintTypesList = new ArrayList<CarMaintTypes>();

        if (dbHandle.isOpen()) {
            try {
                // Clear all rows from table
                dbHandle.execSQL(SQL_DELETE_ROWS_FROM_CAR_MAINTENANCE_TYPES_TABLE);

                //Build list containing all car maintenance types
                maintTypesList.add(createMaintType("Brake Change"));
                maintTypesList.add(createMaintType("Oil Change"));
                maintTypesList.add(createMaintType("Front Rotor Replacement"));
                maintTypesList.add(createMaintType("Rear Rotor Replacement"));
                maintTypesList.add(createMaintType("New Tires"));
                maintTypesList.add(createMaintType("Muffler Replacement"));
                maintTypesList.add(createMaintType("Transmission Rebuild"));
                maintTypesList.add(createMaintType("Spark Plug Replacement"));
                maintTypesList.add(createMaintType("Distributor Rotor Replacement"));
                maintTypesList.add(createMaintType("Distributor Cap Replacement"));
                maintTypesList.add(createMaintType("Alternator Replacement"));
                maintTypesList.add(createMaintType("Water Pump Replacement"));
                maintTypesList.add(createMaintType("Other"));


                // Insert each Maintenance Type into the database
                for (CarMaintTypes l : maintTypesList) {
                    addMaintenanceType(l, dbHandle);
                }
            } catch (android.database.SQLException e) {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.getAllMainteanceTypes(): Exception occured performing database query " + e.getMessage());
            }
        } else {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.getAllMainteanceTypes(): Database is not open for loading all CarInfo records");
        }
    }

    /*
    * Method Name - createMaintType()
    * Input Parameters - String typeName
    * 		name of the maintenance type
    * Return Parameters: CarMaintTypes
    * Purpose:
    * Utility function to create a instance of the CarMaintType and set the name of the type to
    * the input parameter
     */
    CarMaintTypes createMaintType(String typeName) {
        return (new CarMaintTypes(typeName));
    }



	/*
	 * SQL Operations for retrieving, adding, updating and deleting a car maintenance records
	 */

    /*
    * Method Name - addMaintenanceRecord()
    * Input Parameters - String typeName
    *		SQLiteDatabase dbHandle
    * 		CarMaintHistory CarHist
    * Return Parameters: CarMaintTypes
    * Purpose:
    *
     */
    public boolean addMaintenanceRecord(SQLiteDatabase dbHandle, CarMaintHistory CarHist) {

        boolean retVal = true;

        if (dbHandle.isOpen()) {
            try {
                ContentValues values = new ContentValues();

                values.put(CarMaintenanceHistory.COLUMN_MAINTENANCE_CAR_ID, String.valueOf(CarHist.getCarMaintenanceID()));
                values.put(CarMaintenanceHistory.COLUMN_MAINTENANCE_TYPE, CarHist.getMaintenanceType());
                values.put(CarMaintenanceHistory.COLUMN_MILEAGE, CarHist.getMaintenanceMileage());
                values.put(CarMaintenanceHistory.COLUMN_MAINTENANCE_DATE, CarHist.getMaintenanceDate());
                values.put(CarMaintenanceHistory.COLUMN_MAINTENANCE_PRICE, CarHist.getMaintenancePrice());
                values.put(CarMaintenanceHistory.COLUMN_MAINTENANCE_COMMENTS, CarHist.getMaintenanceComments());

                dbHandle.insert(GreaseWrenchContract.CarMaintenanceHistory.TABLE_NAME, null, values);

            } catch (android.database.SQLException e) {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.addMaintenanceRecord(): Exception occured performing database query " + e.getMessage());
                retVal = false;
            }
        } else {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.addMaintenanceRecord(): Database is not open for loading all CarInfo records");
            retVal = false;
        }

        return retVal;
    }

    /*
    * Method Name - getCarMaintenanceRecord()
    * Input Parameters
    *		SQLiteDatabase dbHandle - handle to the SQlite database
            long ID
    * Return Parameters
    *		List<CarMaintHistory> MaintRecordList - a list contain all the data from the Car Maintenance History table for the
    *			specified car or null if no data was retrieved
    * Purpose:
    *		Retrieve all the data from the Car Maintenance Type table
     */
    public List<CarMaintHistory> getCarMaintenanceRecord(SQLiteDatabase dbHandle, long ID) {
        List<CarMaintHistory> MaintRecordList = new ArrayList<CarMaintHistory>();

        String[] allColumns = {COLUMN_ID,
                CarMaintenanceHistory.COLUMN_MAINTENANCE_CAR_ID,
                CarMaintenanceHistory.COLUMN_MAINTENANCE_TYPE,
                CarMaintenanceHistory.COLUMN_MILEAGE,
                CarMaintenanceHistory.COLUMN_MAINTENANCE_DATE,
                CarMaintenanceHistory.COLUMN_MAINTENANCE_PRICE,
                CarMaintenanceHistory.COLUMN_MAINTENANCE_COMMENTS};

        if (dbHandle.isOpen()) {
            try {
                Cursor cursor = dbHandle.query(CarMaintenanceHistory.TABLE_NAME, allColumns,
                        CarMaintenanceHistory.COLUMN_MAINTENANCE_CAR_ID + "=?",
                        new String[]{String.valueOf(ID)}, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        CarMaintHistory maintHist = cursorToMaintHist(cursor);
                        MaintRecordList.add(maintHist);
                        cursor.moveToNext();
                    }
                    cursor.close();
                }
            } //try
            catch (android.database.SQLException e) {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.getCarMaintenanceRecord(): Exception occured performing database query " + e.getMessage());
            }
        } else {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.getCarMaintenanceRecord(): Database is not open for read");
        }


        return (MaintRecordList);
    }

    /*
    * Method Name - cursorToMaintHist()
    * Input Parameters
    * Return Parameters
    * Purpose:
     */
    private CarMaintHistory cursorToMaintHist(Cursor cursor) {
        CarMaintHistory maintHist = new CarMaintHistory();
        maintHist.setCarMaintenanceID(cursor.getLong(1));
        maintHist.setMaintenanceType(cursor.getString(2));
        maintHist.setMaintenanceMileage(cursor.getString(3));
        maintHist.setCarMaintenanceDate(cursor.getString(4));
        maintHist.setMaintenancePrice(cursor.getString(5));
        maintHist.setCarMaintenanceComments(cursor.getString(6));
        return maintHist;
    }

    /*
    * Method Name - deleteCarMaintenanceRecord()
    * Input Parameters
    *		SQLiteDatabase dbHandle
    *		CarMaintTypes Type
    * Return Parameters
    *		void
    * Purpose:
     */
    public void deleteCarMaintenanceRecord(SQLiteDatabase dbHandle, CarMaintHistory CarHist) {
        try {
            if (dbHandle.isOpen()) {
                dbHandle.delete(CarMaintenanceHistory.TABLE_NAME,
                        CarMaintenanceHistory.COLUMN_MAINTENANCE_CAR_ID + "=?",
                        new String[]{String.valueOf(CarHist.getCarMaintenanceID())});
            } // if dbHandle.isOpen()
            else {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.deleteCarMaintenanceRecord(): Database is not open for deleting a record");
            }
        } //try
        catch (android.database.SQLException e) {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.deleteCarMaintenanceRecord(): Exception occured performing database delete " + e.getMessage());
        }

    }

    public void loadCarHistRecords(SQLiteDatabase dbHandle) {

        List<CarMaintHistory> MaintRecordList = new ArrayList<CarMaintHistory>();

        if (dbHandle.isOpen()) {
            try {
                // Clear all rows from table
                dbHandle.execSQL(SQL_DELETE_CAR_MAINTENANCE_HISTORY_ROWS);

                MaintRecordList.add(createCarHist("Brake Change", "130000", "02/06/2017", "250", "Front only", 1));
                MaintRecordList.add(createCarHist("Oil Change", "130000", "01/06/2016", "25", "None", 1));
                MaintRecordList.add(createCarHist("Oil Change", "133000", "05/06/2017", "27", "None", 1));
                MaintRecordList.add(createCarHist("Oil Change", "136000", "08/06/2017", "27", "None", 1));

                for (CarMaintHistory Info : MaintRecordList) {
                    addMaintenanceRecord(dbHandle, Info);
                }
            } catch (android.database.SQLException e) {
                Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.loadCarHistRecords(): Exception occured performing database query " + e.getMessage());
            }
        } else {
            Log.d(android.content.ContentValues.TAG, "GreaseWrechDBHelper.loadCarHistRecords(): Database is not open for loading all CarInfo records");
        }
    }

    /*
    * Method Name - createCarHist()
    * Input Parameters
    * Return Parameters
    * Purpose:
    */
    public CarMaintHistory createCarHist(String MaintType, String MaintMileage, String MaintDate, String MaintPrice, String MaintComments, long CarID) {
        return (new CarMaintHistory(MaintType, MaintMileage,
                MaintDate, MaintPrice, MaintComments, CarID));
    }


}
