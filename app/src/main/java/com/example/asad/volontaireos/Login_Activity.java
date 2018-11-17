package com.example.asad.volontaireos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import com.example.asad.volontaireos.Model.User;

public class Login_Activity extends AppCompatActivity {


    FirebaseDatabase database ;
    DatabaseReference users ;

    EditText editPassword,editUsername ;
    Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        //Firebase ;
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singIn(editUsername.getText().toString(),
                       editPassword.getText().toString());
            }
        });

    }

    private void singIn(final String username, final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()) {
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if (login.getPassword().equals(password)) {
                            Toast.makeText(Login_Activity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                            Intent s = new Intent(getApplicationContext(), Main_Page.class);
                            startActivity(s);
                        }
                        else {
                            Toast.makeText(Login_Activity.this, "Invalid password ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(Login_Activity.this , "Username is not registered" , Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
