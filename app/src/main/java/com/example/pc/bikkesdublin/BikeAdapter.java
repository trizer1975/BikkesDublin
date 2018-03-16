// TRIZER JONSENI KALITSIRO (15295)
package com.example.pc.bikkesdublin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pc on 14/03/2018.
 */

public class BikeAdapter extends ArrayAdapter<Bike> {

    //to reference the Activity
    public BikeAdapter(Context context, List<Bike> bikes) {
        super(context, 0, bikes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       Bike bike = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bike, parent, false);
        }
// Get title element
        TextView bikeTV = (TextView) convertView.findViewById(R.id.bike);

        bikeTV.setText(bike.getName()+"\n"+bike.getAddress()+"\n"+bike.getLatitude()+"\n"+bike.getLongitude());

        return convertView;
    }
}