package com.example.asad.volontaireos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.asad.volontaireos.Infodata;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Form extends AppCompatActivity {

    EditText editTextName;
    Button butAdd;

    DatabaseReference databaseForm;

    private FirebaseAuth mAuth ;
    private FirebaseAuth.AuthStateListener firebaseAuthListener ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        databaseForm=FirebaseDatabase.getInstance().getReference("formartist");

        mAuth = FirebaseAuth.getInstance();


        editTextName = (EditText) findViewById(R.id.editTextName);
        butAdd = (Button) findViewById(R.id.buttonAddDesc);

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        addInfo();
            }
        });

    }

        private void addInfo(){
            String name= editTextName.getText().toString().trim();

            if (!TextUtils.isEmpty(name)){


                String id = databaseForm.push().getKey();
                 name = databaseForm.push().getKey();

                Infodata  infodata = new Infodata(id,name);


              databaseForm.child(id).setValue(infodata);

              Toast.makeText(this,"info added",Toast.LENGTH_LONG).show();


            }else{
                Toast.makeText(this,"You should enter a name", Toast.LENGTH_LONG ).show();
            }
        }

    }

