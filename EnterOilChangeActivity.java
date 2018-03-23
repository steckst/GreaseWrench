package bonacsoftware.com.greasewrench;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EnterOilChangeActivity extends AppCompatActivity {

    SQLiteDatabase dbHandle = null;
    GreaseWrenchDBHelper dbHelper = null;
    private ConstraintLayout localLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_oil_change);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // displays the <- back button in tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final Bundle userSelectedCar = intent.getExtras();

        // Display the user selected car data
        TextView carMake = findViewById(R.id.carMake);
        carMake.setText(userSelectedCar.getString(MainActivity.CAR_MAKE));

        TextView carModel = findViewById(R.id.carModel);
        carModel.setText(intent.getStringExtra(MainActivity.CAR_MODEL));

        TextView carYear = findViewById(R.id.carYear);
        carYear.setText(userSelectedCar.getString(MainActivity.CAR_YEAR));


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void buttonSubmitOnclick(View view) {

        TextView dateOfOilChg   = findViewById(R.id.enterDate);
        TextView mileage = findViewById(R.id.enterMileage);

        dbHelper = new GreaseWrenchDBHelper(getApplicationContext());

        dbHandle = dbHelper.getWritableDatabase();

        SelectedCarInfo theCar = (SelectedCarInfo)getApplication();
        CarInfo carInfo = theCar.getData();

        CarMaintHistory histRecord = new CarMaintHistory(
                getString(R.string.oilChangeStr), mileage.getText().toString(),dateOfOilChg.getText().toString(),"20","",carInfo.getCarID());

        if (dbHelper.addMaintenanceRecord(dbHandle, histRecord) == true) {
            Toast.makeText(view.getContext(),"New Maintenance Record Added",Toast.LENGTH_LONG).show();
        }

        dbHandle.close();

        //Clear out data entry fields
        dateOfOilChg.setText("");
        mileage.setText("");


// public void addMaintenanceRecord(SQLiteDatabase dbHandle, CarMaintHistory CarHist)
//public CarMaintHistory(String MaintType, String MaintMileage,
//        String MaintDate, String MaintPrice,
//                String MaintComments, long CarID)
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
