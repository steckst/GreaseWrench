package bonacsoftware.com.greasewrench;

import android.app.Application;

/**
 * Created by steckst on 3/9/2018.
 */

public class SelectedCarInfo extends Application {
    private CarInfo carInfo;

    // Restrict the constructor rom being instantiated
    public SelectedCarInfo(){}

    public void setData(CarInfo data){
        this.carInfo = data;
    }

    public CarInfo getData () {
        return this.carInfo;
    }

    public synchronized CarInfo getInstance(){
        if (carInfo == null){
            carInfo = new CarInfo();
        }
        return carInfo;
    }
}
