package com.example.asad.volontaireos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


// LGS stands for Let's Get Started Page
// Need to add Image Button
// Need to add Image

public class L_G_S_R extends AppCompatActivity {
    private Handler mHandler = new Handler();

    private static int TIME_OUT = 3000; //Time to launch the another activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgs_r);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(L_G_S_R.this, A6_RM.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}



