package bonacsoftware.com.greasewrench;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import bonacsoftware.com.greasewrench.GreaseWrenchDBHelper;

import java.util.ArrayList;
import java.util.List;

public class SelectCarActivity extends AppCompatActivity {

    SQLiteDatabase dbHandle = null;
    GreaseWrenchDBHelper dbHelper = null;
    List<CarInfo> carInfoList = new ArrayList<CarInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // displays the <- back button in tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Retrieve the all the Cars in the database
        dbHelper = new GreaseWrenchDBHelper(getApplicationContext());
        dbHandle = dbHelper.getReadableDatabase();
//        dbHandle = dbHelper.getWritableDatabase();
//        dbHelper.loadCarInfo(dbHandle);
        carInfoList = dbHelper.getAllCarInfo(dbHandle);
        dbHandle.close();;

        CarListAdapter listOfCarsAdapter = new CarListAdapter(this, R.layout.select_car_list,carInfoList);
        ListView selectCarList = (ListView) findViewById(R.id.selectCarList);
        selectCarList.setAdapter(listOfCarsAdapter);

        selectCarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // retrieve the car the user selected
                CarInfo carSelected = carInfoList.get(i);
                // need to return the car information the user selected to the parent
                // activity
                Intent carInfoData = new Intent();
                Bundle dataBundle = new Bundle();
                dataBundle.putString(MainActivity.CAR_MAKE,carSelected.getCarMake());
                dataBundle.putString(MainActivity.CAR_MODEL,carSelected.getCarModel());
                dataBundle.putString(MainActivity.CAR_YEAR,carSelected.getCarYear());
                dataBundle.putString(MainActivity.CAR_MILEAGE,carSelected.getCarMileage());
                dataBundle.putLong(MainActivity.CAR_ID,carSelected.getCarID());
                //carInfoData.putExtra(MainActivity.RETURN_CAR_INFO,dataBundle);
                carInfoData.putExtras(dataBundle);
                setResult(RESULT_OK, carInfoData);
                finish();
            }
        });
    }



    String[] loadCarInfoIntoAdapter (List<CarInfo> carInfoList){
        String[] carMakeList = new String[carInfoList.size()];

        int size = carInfoList.size();
        for (int i=0;i<size;i++){
            carMakeList[i] =  carInfoList.get(i).getCarMake();
        }

        return carMakeList;
    }

}
