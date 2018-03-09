package bonacsoftware.com.greasewrench;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import bonacsoftware.com.greasewrench.GreaseWrenchDBHelper;

public class NewCarActivity extends AppCompatActivity {

    SQLiteDatabase dbHandle = null;
    GreaseWrenchDBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);

        Button submitNewCarButton = (Button) findViewById(R.id.submitNewCar);
        submitNewCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // User pressed the submit button. We need to capture the data enter
                // by the user and put it into the database
                CarInfo carInfo = new CarInfo();  // Create a CarInfo to store data user entered

                // get the data the user typed in
                EditText carMake = (EditText) findViewById(R.id.carMake);
                carInfo.setCarMake(carMake.getText().toString());

                EditText carModel = (EditText) findViewById(R.id.carModel);
                carInfo.setCarModel(carModel.getText().toString());

                EditText carYear = (EditText) findViewById(R.id.carYear);
                carInfo.setCarYear(carYear.getText().toString());

                EditText carMileage = (EditText) findViewById(R.id.carMileage);
                carInfo.setCarMileage(carMileage.getText().toString());

                // setup connection to the database and add new car to DB
                dbHelper = new GreaseWrenchDBHelper(getApplicationContext());

                dbHandle = dbHelper.getWritableDatabase();

                dbHelper.addCarInformation(carInfo,dbHandle);  // add new car to DB

                //new Intent(this, NewCarActivity.class);
            }
        });

    }
}
