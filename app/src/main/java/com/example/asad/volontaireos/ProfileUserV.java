package com.example.asad.volontaireos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileUserV extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    TextView profileTxt;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user_v);
        auth = FirebaseAuth.getInstance();
        profileTxt = (TextView) findViewById(R.id.textViewtwo);
        user = auth.getCurrentUser();

        profileTxt.setText(user.getEmail());


        reference = FirebaseDatabase.getInstance().getReference().child(user.getUid());

    }


    }


