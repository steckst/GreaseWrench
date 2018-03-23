package bonacsoftware.com.greasewrench;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by steckst on 2/23/2018.
 */

public class CarListAdapter  extends ArrayAdapter<CarInfo>{

    private List<CarInfo> listOfCars;

    public CarListAdapter(Context context, int resource, List<CarInfo> objects) {
        super(context, resource);
        this.listOfCars = objects;
    }
   @Override
    public int getCount(){
        return this.listOfCars.size();
    }

    @Nullable
    @Override
    public CarInfo getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.select_car_list,parent,false);
        }
        CarInfo carInfo = listOfCars.get(position);

        TextView carMake = (TextView) convertView.findViewById(R.id.carMake);
        carMake.setText(carInfo.getCarMake());

        TextView carModel = (TextView) convertView.findViewById(R.id.carModel);
        carModel.setText(carInfo.getCarModel());

        TextView carYear = (TextView) convertView.findViewById(R.id.carYear);
        carYear.setText(carInfo.getCarYear());

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
