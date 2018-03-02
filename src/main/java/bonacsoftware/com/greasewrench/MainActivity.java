package bonacsoftware.com.greasewrench;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import bonacsoftware.com.greasewrench.GreaseWrenchDBHelper;

public class MainActivity extends AppCompatActivity {

    GreaseWrenchDBHelper dbHelper = null;
    public CarInfo selectedCar = null;

    //Constants for passing CarInfo data between Activities
    private static final int SELECT_CAR_REQUEST = 1111;
    public static final String RETURN_CAR_INFO = "RETURN_CARINFO";
    public static final String CAR_MAKE = "CARMAKE";
    public static final String CAR_MODEL = "CARMODEL";
    public static final String CAR_YEAR = "CARYEAR";
    public static final String CAR_MILEAGE = "CARMILEAGE";
    public static final String CAR_ID = "CARID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button selectCarButton = (Button) findViewById(R.id.selectCarButton);
        selectCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
            }
        });

        // On Click action method which will start the enter NewCarActivity

        Button enterNewCarButton = (Button) findViewById(R.id.enterNewCarButton);
        enterNewCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent newCarIntent = new Intent();
                newCarIntent.setClass(MainActivity.this, NewCarActivity.class);
                startActivity(newCarIntent);


            }
        });



        // On Click action method which will start the SelectCarActivity

        Button selCarButton = (Button) findViewById(R.id.selectCarButton);
        selCarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent selectCarIntent = new Intent();
                selectCarIntent.setClass(MainActivity.this, SelectCarActivity.class);
                startActivityForResult(selectCarIntent, SELECT_CAR_REQUEST);
//                startActivity(selectCarIntent);


            }
        });

//        dbHelper = new GreaseWrenchDBHelper(getApplicationContext());

 //       SQLiteDatabase db = dbHelper.getWritableDatabase();
 //       dbHelper.loadCarInfo( db);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SELECT_CAR_REQUEST) {
            if (resultCode == RESULT_OK) {
                selectedCar = new CarInfo();
                Bundle userSelectedCar = data.getExtras();
                selectedCar.setCarMake(userSelectedCar.getString(CAR_MAKE));
                selectedCar.setCarModel(userSelectedCar.getString(CAR_MODEL).toString());
                selectedCar.setCarYear(userSelectedCar.getString(CAR_YEAR).toString());
                selectedCar.setCarMileage(userSelectedCar.getString(CAR_MILEAGE).toString());
                selectedCar.setCarID(userSelectedCar.getLong(CAR_ID));

                Intent carActivity = new Intent();
                carActivity.setClass(MainActivity.this, CarActivity.class);
//        startActivityForResult(carActivity, SELECT_CAR_REQUEST);
                startActivity(carActivity);

            }
        }



    }

}
