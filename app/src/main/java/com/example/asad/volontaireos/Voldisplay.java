package com.example.asad.volontaireos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.asad.volontaireos.Infodata;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Voldisplay extends AppCompatActivity {
    DatabaseReference databaseForm;

    ListView listViewForm;
    List<Infodata> formList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voldisplay);

        databaseForm=FirebaseDatabase.getInstance().getReference("Request_Form");
        listViewForm = (ListView) findViewById(R.id.listViewForm);

        formList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseForm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                formList.clear();
                for (DataSnapshot formSnapshot : dataSnapshot.getChildren()){

                    Infodata infodata =formSnapshot.getValue(Infodata.class);


                    formList.add(infodata);
                }

                FormList adapter = new FormList(Voldisplay.this,formList);
                listViewForm.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

