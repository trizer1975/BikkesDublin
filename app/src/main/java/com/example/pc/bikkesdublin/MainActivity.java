package com.example.pc.bikkesdublin;
// TRIZER JONSENI KALITSIRO (15295)
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button listOfBikesButton ;
    Button map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listOfBikesButton = findViewById(R.id.button);
        map = findViewById(R.id.button1);

        listOfBikesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(MainActivity.this, ListBikeActivity.class));
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this is when you click to jump to another a ctivity
             startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });
    }
}
