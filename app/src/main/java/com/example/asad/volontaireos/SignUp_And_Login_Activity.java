package com.example.asad.volontaireos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class SignUp_And_Login_Activity  extends AppCompatActivity {

    Button btn_SignUp, btn_Login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);


        btn_SignUp = (Button) findViewById(R.id.btn_SignUp);
        btn_Login = (Button) findViewById(R.id.btn_Login);

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Sign_Up_Activity.class);
                startActivity(a);

            }
        });

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(b);

            }
        });
    }

}




