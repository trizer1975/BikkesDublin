// TRIZER JONSENI KALITSIRO (15295)
package com.example.pc.bikkesdublin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListBikeActivity extends AppCompatActivity {
// creating a list View
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bike);
        listView = findViewById(R.id.listview);
        new fetchData().execute();
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
            BikeAdapter bikeAdapter = new BikeAdapter(ListBikeActivity.this, bikes);
            listView.setAdapter(bikeAdapter);
        }

}
}
