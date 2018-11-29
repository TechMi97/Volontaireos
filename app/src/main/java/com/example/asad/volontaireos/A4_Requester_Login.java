package com.example.asad.volontaireos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class A4_Requester_Login extends AppCompatActivity {
    private EditText mEmail, mPassword ;
    private Button mLogIn , mRegister ;

    private FirebaseAuth mAuth ;
    private FirebaseAuth.AuthStateListener firebaseAuthListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3_requester_login);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent b  =  new Intent(A4_Requester_Login.this,A6_RM.class);
                    startActivity(b);
                    finish();
                    return;
                }
            }
        };

        mEmail    = (EditText) findViewById(R.id.editEmail);
        mPassword = (EditText) findViewById(R.id.editPassword);

        mLogIn    = (Button) findViewById(R.id.btn_To_SignIn);
        mRegister = (Button) findViewById(R.id.btnSignUp);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(A4_Requester_Login.this,A7_Rsignup.class);
                startActivity(b);
                finish();
                return;
            }
        });




        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(A4_Requester_Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                            Toast.makeText(A4_Requester_Login.this,"Sign in sucessfullll",Toast.LENGTH_SHORT).show();

                        if (!task.isSuccessful())
                            Toast.makeText(A4_Requester_Login.this,"Sign in error",Toast.LENGTH_SHORT).show();

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

