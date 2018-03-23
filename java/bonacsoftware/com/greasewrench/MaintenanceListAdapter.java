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

public class MaintenanceListAdapter extends ArrayAdapter<CarInfo>{

    private List<CarMaintHistory> listOfMaintRecs;

    public MaintenanceListAdapter(Context context, int resource, List<CarMaintHistory> objects) {
        super(context, resource);
        this.listOfMaintRecs = objects;
    }
   @Override
    public int getCount(){
        return this.listOfMaintRecs.size();
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
                    inflate(R.layout.car_maint_display_list,parent,false);
        }
        CarMaintHistory maintInfo = listOfMaintRecs.get(position);

        TextView carMake = (TextView) convertView.findViewById(R.id.maintType);
        carMake.setText(maintInfo.getMaintenanceType());

        TextView carModel = (TextView) convertView.findViewById(R.id.maintDate);
        carModel.setText(maintInfo.getMaintenanceDate());

        TextView carYear = (TextView) convertView.findViewById(R.id.maintMileage);
        carYear.setText(maintInfo.getMaintenanceMileage());

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
