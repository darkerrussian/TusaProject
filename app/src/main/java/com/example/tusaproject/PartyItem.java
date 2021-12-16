package com.example.tusaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    DatabaseReference reference;
    FirebaseDatabase database;
    Integer counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_item);
        intent = getIntent();
        String count = intent.getStringExtra("count");
        int image = intent.getExtras().getInt("imageUrl");
        String location = intent.getStringExtra("location_party");
        String partyName = intent.getStringExtra("nameParty");
        //String partyPath = intent.getStringExtra("partyPath");

        ArrayList<String> partyUsers = intent.getExtras().getStringArrayList("partyUsers");

       // Log.println(Log.ERROR,"usersinfotest",partyUsers.toString());

        img = findViewById(R.id.personal_img);
        txt = findViewById(R.id.personal_text);
        joinButton = findViewById(R.id.joinprt);




        listView = findViewById(R.id.list_users);

        //Join Button Code
        String partyPath = "party_" + String.valueOf(count) + partyName;
        reference = database.getInstance().getReference("Parties").child(partyPath).child("users");
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                auth = FirebaseAuth.getInstance();
                firebaseUser = auth.getCurrentUser();
                String currentUserMail = firebaseUser.getEmail();

                //test = test.substring(1, test.length()-1);
                //Log.println(Log.INFO,"usersinfotest",test);

           /*     for(int k =0; k<partyUsers.size(); k++){
                    if(currentUserMail != partyUsers.get(k)){

                        Log.println(Log.INFO,"2","adding user");
                    }
                    else{
                        counter++;
                    }
                }
                Log.println(Log.INFO,"usersinfotest",counter.toString());
                if(counter==0) {
                    partyUsers.add(currentUserMail);

                    reference.setValue(partyUsers);
                }*/



                Log.println(Log.INFO,"usersinfotest",partyUsers.toString());

               for(String userMail : partyUsers){

                    if(currentUserMail == userMail){
                        ++counter;


                    }

                }
                Log.println(Log.INFO,"usersinfotest",counter.toString());
                if(counter==0){
                    partyUsers.add(currentUserMail);


                   // reference.setValue(partyUsers);

                }
                Log.println(Log.INFO,"usersinfotest",partyUsers.toString());







            }
        });





        //end join button code







        img.setImageResource(image);
        txt.setText(count);


        //sdsd

        //Test code for sending location to Map

        Bundle bundle = new Bundle();
        bundle.putString("location_to_map",location);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, partyUsers);
        listView.setAdapter(arrayAdapter);

        //End testing code
        Fragment fragment = new MapFragment();
        fragment.setArguments(bundle);
        //jhjh
        //Open fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.map_layout, fragment).commit();
    }
}