package com.example.tusaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tusaproject.interfaces.PassDataInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreatePartyItem extends AppCompatActivity implements PassDataInterface {
    int number;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button button;
    EditText nameParty, maxParty;
    TextView textView;
    String location;
    public ArrayList<Party> party = new ArrayList<>();
    CreateMapFragment createMapFragment;
    String data;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    ArrayList<FirebaseUser> firebaseUsersList;
    ArrayList<String> usersMails;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_party_item);
        button = findViewById(R.id.button_createprt);
        nameParty = findViewById(R.id.name_party);
        maxParty = findViewById(R.id.number_party);
        textView = findViewById(R.id.location_text);

        

        Fragment fragment = new CreateMapFragment(CreatePartyItem.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.create_map_fragment, fragment).commit();

        //Button listener Create Party

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = nameParty.getText().toString();
                String numms = maxParty.getText().toString();

                String num = "party_" + String.valueOf(numms) + name;









               if(data == null){
                    Toast.makeText(CreatePartyItem.this, "Set the Location", Toast.LENGTH_LONG);
                }
               else {


                    CreateParty(name, numms, num, data);


                    Intent intent = new Intent(CreatePartyItem.this, MainActivity.class);
                    startActivity(intent);
                }


            }
        });




    }

    public void CreateParty(String name, String nums, String number, String location){
        Map<String, String> hashMap = new HashMap<>();
        //new code
        ArrayList <FirebaseUser> firebaseUsers = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        firebaseUsers.add(auth.getCurrentUser());
        //new code
        hashMap.put("Name", name);
        hashMap.put("numbers", nums);

        hashMap.put("location", location);

        //reference = database.getInstance().getReference("Parties").child(number);
        reference = database.getInstance().getReference("Parties").child(number);
        firebaseUser = firebaseUsers.get(0);
        reference.setValue(hashMap);
        reference = database.getInstance().getReference("Parties").child(number).child("users");
        //test

        for(FirebaseUser user : firebaseUsers){
            reference.setValue(user.getEmail());

        }

        //end
       // reference.setValue(firebaseUser.getEmail());

        //reference.setValue(firebaseUser.getEmail());
        Party currentParty = new Party(name, nums, R.drawable.party_1,location);
        currentParty.setUsersList(firebaseUsers);
        usersMails = new ArrayList<>();
        usersMails.add(firebaseUser.getEmail());
        currentParty.setUsersMails(usersMails);
//        currentParty.setPartyPath(number);
        party.add(currentParty);
        //currentParty.setUsersList(firebaseUsers);







    }

    @Override
    public void onDataReceived(String data) {

        textView.setText(data);
        this.data = data;

    }
}