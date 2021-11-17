package com.example.tusaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    EditText userETLogin, passETLogin;
    Button LoginBtn, RegisterBtn;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    DatabaseReference ref;
    FirebaseDatabase base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ref = base.getInstance().getReference("DA?");
        ref.setValue("blyeat");


        userETLogin = findViewById(R.id.editTextLogin);
        passETLogin = findViewById(R.id.editText);
        LoginBtn = findViewById(R.id.buttonLogin);
        RegisterBtn = findViewById(R.id.registerBtn);
        auth = FirebaseAuth.getInstance();

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register_Activity.class);

                startActivity(i);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = userETLogin.getText().toString();
                String pass_text = passETLogin.getText().toString();

                //Checking if it empty

                if(TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)){
                    Toast.makeText(Login.this,"Please fill the fieldx" , Toast.LENGTH_SHORT);
                }else{
                    auth.signInWithEmailAndPassword(email_text, pass_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i = new Intent(Login.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                            }
                            else {
                                Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }
            }
        });

    }
}