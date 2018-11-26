package com.example.asad.volontaireos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class A1_Main extends AppCompatActivity {

    private Button mVolunteer ,mRequester ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a1_main_page);

        mVolunteer = (Button) findViewById(R.id.btnV);
        mRequester  = (Button) findViewById(R.id.btnR);

        mVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(A1_Main.this,A2_Volunteer_Login.class);
                startActivity(a);
                finish();
                return;
            }
        });

        mRequester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(A1_Main.this,A4_Requester_Login.class);
                startActivity(b);
                finish();
                return;
            }
        });
    }
}
