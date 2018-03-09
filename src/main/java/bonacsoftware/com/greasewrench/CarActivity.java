package bonacsoftware.com.greasewrench;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final Bundle userSelectedCar = intent.getExtras();

        // Display the user selected car data
        TextView carMake = findViewById(R.id.ccarMake);
        carMake.setText(userSelectedCar.getString(MainActivity.CAR_MAKE));

        TextView carModel = findViewById(R.id.ccarModel);
        carModel.setText(intent.getStringExtra(MainActivity.CAR_MODEL));

        TextView carYear = findViewById(R.id.ccarYear);
        carYear.setText(userSelectedCar.getString(MainActivity.CAR_YEAR));

        TextView carMileage = findViewById(R.id.ccarMileage);
        carMileage.setText(userSelectedCar.getString(MainActivity.CAR_MILEAGE));



        //steup button on click listeners
        Button oilChangeCarButton = (Button) findViewById(R.id.buttonOilChange);
        oilChangeCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent oilChangeIntent = new Intent();
                oilChangeIntent.setClass(CarActivity.this, EnterOilChangeActivity.class);
                oilChangeIntent.putExtras(userSelectedCar);
                startActivity(oilChangeIntent);
            }
        });



/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
