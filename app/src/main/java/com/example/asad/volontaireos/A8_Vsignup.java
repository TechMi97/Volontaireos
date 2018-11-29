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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class A8_Vsignup extends AppCompatActivity {

    private EditText mEmail, mPassword ,mAge,mIC,mNation,mName ;
    private Button mRegister ;

    private FirebaseAuth mAuth ;
    private FirebaseAuth.AuthStateListener firebaseAuthListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a8__vsignup);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent b  =  new Intent(A8_Vsignup.this,A5_VM.class);
                    startActivity(b);
                    finish();
                    return;
                }
            }
        };

        mEmail    = (EditText) findViewById(R.id.editEmail);
        mPassword = (EditText) findViewById(R.id.editPassword);
        mAge = (EditText) findViewById(R.id.editAge);
        mIC = (EditText) findViewById(R.id.editIC);
        mNation = (EditText) findViewById(R.id.editNation);
        mName = (EditText) findViewById(R.id.editUsername);
        mRegister = (Button) findViewById(R.id.btnSignUp);



        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                final String age = mAge.getText().toString();
                final String IC = mIC.getText().toString();
                final String nation = mNation.getText().toString();
                final String name = mName.getText().toString();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(A8_Vsignup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful())
                            Toast.makeText(A8_Vsignup.this,"Sign up error",Toast.LENGTH_SHORT).show();
                        else{
                            Toast.makeText(A8_Vsignup.this,"Signuppp success",Toast.LENGTH_SHORT).show();
                            String user_id = mAuth.getCurrentUser().getUid();

                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference()
                                    .child("Users_A").child("Volunteers").child(user_id).child("email");
                            current_user_db.setValue(email);
                            current_user_db = FirebaseDatabase.getInstance().getReference().child("Users_A").child("Volunteers").child(user_id).child("password");
                            current_user_db.setValue(password);
                            current_user_db = FirebaseDatabase.getInstance().getReference().child("Users_A").child("Volunteers").child(user_id).child("Age");
                            current_user_db.setValue(age);
                            current_user_db = FirebaseDatabase.getInstance().getReference().child("Users_A").child("Volunteers").child(user_id).child("IC");
                            current_user_db.setValue(IC);
                            current_user_db = FirebaseDatabase.getInstance().getReference().child("Users_A").child("Volunteers").child(user_id).child("Nationality");
                            current_user_db.setValue(nation);
                            current_user_db = FirebaseDatabase.getInstance().getReference().child("Users_A").child("Volunteers").child(user_id).child("Name");
                            current_user_db.setValue(name);


                        }
                    }
                });

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    };

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    };
}
