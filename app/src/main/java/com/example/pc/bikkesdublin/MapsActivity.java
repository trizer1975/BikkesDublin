package com.example.pc.bikkesdublin;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONException;
// TRIZER JONSENI KALITSIRO (15295)
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public class fetchData extends AsyncTask<Void, Void, List<Bike>> {
        String data = "";
        List<Bike> bikes = new ArrayList<>();
        Gson gson = new Gson();

        @Override
        protected List<Bike> doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.myjson.com/bins/d7hyp");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line ="";
                while (line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }
                JSONObject JA = new JSONObject(data);
                for (int i = 0; i < JA.getJSONArray("bikes").length(); i++){
                    bikes.add(gson.fromJson(JA.getJSONArray("bikes").getString(i), Bike.class));
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return bikes;
        }

        @Override
        protected void onPostExecute(List<Bike> bikes) {
            super.onPostExecute(bikes);
            for(Bike bike : bikes){
                LatLng position = new LatLng( bike.getLatitude(), bike.getLongitude());
                mMap.addMarker(new MarkerOptions().position(position).title(bike.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
            }

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        new fetchData().execute();
    }
 }

// TRIZER JONSENI KALITSIRO (15295)