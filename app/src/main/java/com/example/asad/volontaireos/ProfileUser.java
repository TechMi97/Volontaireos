package com.example.asad.volontaireos;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileUser extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    TextView profileTxt;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        auth = FirebaseAuth.getInstance();
        profileTxt = (TextView) findViewById(R.id.textView2);
        user = auth.getCurrentUser();

        profileTxt.setText(user.getEmail());


        reference = FirebaseDatabase.getInstance().getReference().child(user.getUid());

    }


    }


