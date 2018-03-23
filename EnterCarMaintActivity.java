package bonacsoftware.com.greasewrench;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class EnterCarMaintActivity extends AppCompatActivity {

    SQLiteDatabase dbHandle=null;
    GreaseWrenchDBHelper dbHelper=null;
    CarInfo userSelectedCar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_car_maint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Global variable containing the user selected car
        SelectedCarInfo theCar = (SelectedCarInfo) getApplication();
        userSelectedCar = theCar.getData();

        // Display the user selected car data
        TextView carMake = findViewById(R.id.carMake);
        carMake.setText(userSelectedCar.getCarMake());

        TextView carModel = findViewById(R.id.carModel);
        carModel.setText(userSelectedCar.getCarModel());

        TextView carYear = findViewById(R.id.carYear);
        carYear.setText(userSelectedCar.getCarYear());

        /*
         * setup onclick listener when user presses button to add a new
         * maintenance record for the current car
         */
        Button oilChangeCarButton = (Button) findViewById(R.id.SubmitMaintEntryButton);
        oilChangeCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                TextView mileageEntered = findViewById(R.id.MileageEntered);
                TextView dateEntered = findViewById(R.id.DateEntered);
                TextView maintTypeEntered = findViewById(R.id.Maintype);
                TextView maintTypePrice = findViewById(R.id.PriceEntered);

                // setup access to the database
                dbHelper = new GreaseWrenchDBHelper(getApplicationContext());
                dbHandle = dbHelper.getWritableDatabase();

                // Create a object to hold the maintenance information we want to add for the car
                CarMaintHistory newCarMaint = new CarMaintHistory(
                        maintTypeEntered.getText().toString(),
                        mileageEntered.getText().toString(),
                        dateEntered.getText().toString(),
                        maintTypePrice.getText().toString(),
                        "",
                        userSelectedCar.getCarID());

                // add the maintenance for the car to the DB
                if (dbHelper.addMaintenanceRecord(dbHandle, newCarMaint) == true) {
                    Toast.makeText(v.getContext(), "New Maintenance Record Added", Toast.LENGTH_LONG).show();
                }

                dbHandle.close();

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
/*
 * On click listener for when user clicks on edit text field to enter maintenance type
 * This method will invoke a list activity to display all possible maintenance types
* */
    public void enterMaintType(View view) {

    }
}
