package com.example.greasewrench;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import java.util.List;


public class MainActivity extends Activity {

	GreaseWrechDBHelper dbHelper = null;
    SQLiteDatabase dbHandle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Log.d(android.content.ContentValues.TAG, "GreaseWrech Starting up.....");
        dbHelper = new GreaseWrechDBHelper(getApplicationContext());
        dbHandle = dbHelper.getWritableDatabase();

        dbHelper.rebuildDBUtility( dbHandle);

        dbHelper.loadMaintenanceTypes(dbHandle);
        dbHelper.loadCarInfo(dbHandle);
        dbHelper.loadCarHistRecords (dbHandle);

        List<CarMaintTypes> maintTypes = dbHelper.getAllMainteanceTypes(dbHandle);
        List<CarInfo> carInforList =  dbHelper.getAllCarInfo(dbHandle);
        List<CarMaintHistory> CarMaintHistList = dbHelper.getCarMaintenanceRecord ( dbHandle, 1);

        CarInfo carInfo = dbHelper.getCarInformation (dbHandle, 3);
        dbHelper.deleteCarInformation(dbHandle, carInfo);

        carInfo=null;

        carInfo = dbHelper.getCarInformation (dbHandle, 3);
        carInfo = dbHelper.getCarInformation (dbHandle, 2);
        carInfo.setCarMileage("111111");
        dbHelper.updateCarInformation (dbHandle, carInfo);

        carInfo=null;

        carInfo = dbHelper.getCarInformation (dbHandle, 2);

        CarMaintTypes Type = dbHelper.getMaintenanceType (dbHandle, 1);
        Type.setMaintenanceType("Updated Type");
        dbHelper.updateMaintenenaceType(dbHandle,Type);
        Type=null;
        Type = dbHelper.getMaintenanceType (dbHandle, 1);

        CarMaintHistory Info = dbHelper.createCarHist("Brake Change","140000","02/06/2017","300", "Front only",2);
        dbHelper.addMaintenanceRecord(dbHandle, Info);

        Info = dbHelper.createCarHist("Brake Change","140000","02/06/2017","300", "Rear only",2);
        dbHelper.addMaintenanceRecord(dbHandle, Info);

        CarMaintHistList = dbHelper.getCarMaintenanceRecord ( dbHandle, 2);
        dbHandle.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	@Override
	protected void onResume() {
        dbHandle = dbHelper.getWritableDatabase();
        super.onResume();
	}

	@Override
	protected void onPause() {
        dbHandle.close();
        super.onPause();
	}

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
