package bonacsoftware.com.greasewrench;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayCarMaintHist extends AppCompatActivity {

    SQLiteDatabase dbHandle = null;
    GreaseWrenchDBHelper dbHelper = null;
    List<CarMaintHistory> carMaintList = new ArrayList<CarMaintHistory>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car_maint_hist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // displays the <- back button in tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         //Global variable containing the user selected car
        SelectedCarInfo theCar = (SelectedCarInfo) getApplication();
        CarInfo userSelectedCar = theCar.getData();

        // Display the user selected car data
        TextView carMake = findViewById(R.id.ccarMake);
        carMake.setText(userSelectedCar.getCarMake());

        TextView carModel = findViewById(R.id.ccarModel);
        carModel.setText(userSelectedCar.getCarModel());

        TextView carYear = findViewById(R.id.ccarYear);
        carYear.setText(userSelectedCar.getCarYear());

        // Retrieve the all of the Cars maintenance record
        dbHelper = new GreaseWrenchDBHelper(getApplicationContext());
        dbHandle = dbHelper.getReadableDatabase();
        carMaintList = dbHelper.getCarMaintenanceRecord(dbHandle, userSelectedCar.getCarID());
        dbHandle.close();


        MaintenanceListAdapter maintHistAdapter = new MaintenanceListAdapter(this, R.layout.car_maint_display_list, carMaintList);
        ListView selectCarList = (ListView) findViewById(R.id.carMaintHistList);
        selectCarList.setAdapter(maintHistAdapter);

    }
    /*
    This method is called when the user presses the <- button
    required for navigation to previous activity
    */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
