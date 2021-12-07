package com.example.tusaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class PartyItem extends AppCompatActivity {
    Intent intent;
    ImageView img;
    TextView txt;
    TextView txtLocation;
    Users user;
    Button joinButton;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_item);
        intent = getIntent();
        String count = intent.getStringExtra("count");
        int image = intent.getExtras().getInt("imageUrl");
        String location = intent.getStringExtra("location_party");

        String [] partyUsers = intent.getExtras().getStringArray("partyUsers");
        img = findViewById(R.id.personal_img);
        txt = findViewById(R.id.personal_text);
        joinButton = findViewById(R.id.joinprt);
        //New code

        listView = findViewById(R.id.list_users);




        //End new code


        img.setImageResource(image);
        txt.setText(count);
        //sdsd

        //Test code for sending location to Map

        Bundle bundle = new Bundle();
        bundle.putString("location_to_map",location);
        /*arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, partyUsers);
        listView.setAdapter(arrayAdapter);*/

        //End testing code
        Fragment fragment = new MapFragment();
        fragment.setArguments(bundle);
        //jhjh
        //Open fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.map_layout, fragment).commit();
    }
}