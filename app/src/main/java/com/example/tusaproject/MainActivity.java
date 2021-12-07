package com.example.tusaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Party> party = new ArrayList<>();
    ImageView image;
    Button btnCreateParty;
    FirebaseDatabase database;
    DatabaseReference reference;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rec = findViewById(R.id.ss);
        btnCreateParty = findViewById(R.id.btn_create);
        image = findViewById(R.id.image_party);
        //party.add(new Party("FIrst", "12/20", R.drawable.party_1));
        number = 1;

        reference = database.getInstance().getReference("Parties");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Party party1 = new Party(dataSnapshot.child("Name").getValue().toString(), dataSnapshot.child("numbers").getValue().toString(), R.drawable.party_1, dataSnapshot.child("location").getValue().toString()) ;
                    try {
                        List<String> getUsers = new ArrayList<>();
                        getUsers.add(dataSnapshot.child("users").getValue().toString());
                        party1.setUsersMails(getUsers);
                    }catch (NullPointerException e){
                        Log.println(Log.ERROR,"error", "null users");
                    }

                    party.add(party1);

                   PartyAdapter adapter = new PartyAdapter(MainActivity.this, party);
                   rec.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });






        btnCreateParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* RecyclerView rec = findViewById(R.id.ss);
                PartyAdapter adapter = new PartyAdapter(MainActivity.this, party);
                rec.setAdapter(adapter);
                String numms = number + "/23";
                String num = "party_" + String.valueOf(number);

                CreateParty("kakashka", numms, num);
                number++;

                */
                Intent intent = new Intent(MainActivity.this, CreatePartyItem.class);
                startActivity(intent);

            }
        });
    }


       /* public void CreateParty(String name, String nums, String number){
            Map <String, String> hashMap = new HashMap<>();

            hashMap.put("numbers", nums);
            hashMap.put("Name", name);

            reference = database.getInstance().getReference("Parties").child(number);
            reference.setValue(hashMap);
            party.add(new Party(name, nums, R.drawable.party_1));







        }

        */








}