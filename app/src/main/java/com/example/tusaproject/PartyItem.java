package com.example.tusaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PartyItem extends AppCompatActivity {
    Intent intent;
    ImageView img;
    TextView txt;
    TextView txtLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_item);
        intent = getIntent();
        String count = intent.getStringExtra("count");
        int image = intent.getExtras().getInt("imageUrl");
        String location = intent.getStringExtra("location_party");
        img = findViewById(R.id.personal_img);
        txt = findViewById(R.id.personal_text);
        txtLocation = findViewById(R.id.text_location);
        img.setImageResource(image);
        txt.setText(count);
        //sdsd
        txtLocation.setText(location);

        //Test code for sending location to Map

        Bundle bundle = new Bundle();
        bundle.putString("location_to_map",location);

        //End testing code
        Fragment fragment = new MapFragment();
        fragment.setArguments(bundle);
        //jhjh
        //Open fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.map_layout, fragment).commit();
    }
}