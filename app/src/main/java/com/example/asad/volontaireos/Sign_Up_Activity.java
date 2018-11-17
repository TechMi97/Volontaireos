package com.example.asad.volontaireos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.example.asad.volontaireos.Model.User;
import com.google.firebase.database.ValueEventListener;


public class Sign_Up_Activity extends AppCompatActivity  {

FirebaseDatabase database ;
DatabaseReference users ;

EditText editPassword,editUsername,editEmail ;
Button btnSignUp, btn_To_SignIn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_a);


        //Firebase ;
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editEmail = (EditText) findViewById(R.id.editEmail);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btn_To_SignIn = (Button) findViewById(R.id.btn_To_SignIn);


        btn_To_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  = new Intent (getApplicationContext(),Login_Activity.class);
                startActivity(a);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final User user = new User (editUsername.getText().toString(),
                        editPassword.getText().toString(),
                        editEmail.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         if (dataSnapshot.child(user.getUsername()).exists())
                             Toast.makeText(Sign_Up_Activity.this,"This Username Already Exists",Toast.LENGTH_SHORT).show();
                         else{
                             users.child(user.getUsername()).setValue(user);
                             Toast.makeText(Sign_Up_Activity.this,"Succesful Registeration ",Toast.LENGTH_SHORT).show();
                         }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

}
