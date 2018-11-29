package com.example.asad.volontaireos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class A9_RChoice extends AppCompatActivity {
    private Button btnvmap ,btnmreq ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a9__rmapreq);

        btnmreq = (Button) findViewById(R.id.btnMReq);
        btnvmap  = (Button) findViewById(R.id.btnVmap);


        btnmreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent a = new Intent(A9_RChoice.this,A11_Rmakereq.class);
                startActivity(a);
                finish();
                return;
            }
        });





        btnvmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(A9_RChoice.this,A10_RVMap.class);
                startActivity(b);
                finish();
                return;
            }
        });
    }
}
