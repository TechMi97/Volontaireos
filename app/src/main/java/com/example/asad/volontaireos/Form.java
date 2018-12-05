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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Form extends AppCompatActivity {

    EditText editTextTitle,editTextDesc;
    Button butAdd,butFinish;
    DatabaseReference databaseForm;


    FirebaseStorage storage ;
    StorageReference storageReference ;

    private ImageButton mCamera;
    private Button mUpload;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    private ImageView imageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        databaseForm=FirebaseDatabase.getInstance().getReference("Request_Form");

        editTextTitle = (EditText) findViewById(R.id.editTextName);
        editTextDesc = (EditText) findViewById(R.id.editTextName2);
        butAdd = (Button) findViewById(R.id.buttonAddDesc);
        butFinish= (Button) findViewById(R.id.button) ;


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        mCamera = (ImageButton) findViewById(R.id.camera);
        mUpload = (Button) findViewById(R.id.upload);
        imageView = (ImageView) findViewById(R.id.imageView);


        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });



        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        addInfo();
            }
        });


        butFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Form.this,A6_RM.class);
                startActivity(a);
                finish();
                return;
            }
        });




    }

    private void uploadImage(){
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Form.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Form.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    private void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    private void addInfo(){
            String Title= editTextTitle.getText().toString().trim();
            String Desc= editTextDesc.getText().toString().trim();

            if (!TextUtils.isEmpty(Title)){


                String id = databaseForm.push().getKey();


                Infodata  infodata = new Infodata(id,Title,Desc);

              databaseForm.child(id).setValue(infodata);

              Toast.makeText(this,"info added",Toast.LENGTH_LONG).show();


            }else{
                Toast.makeText(this,"You should enter a name", Toast.LENGTH_LONG ).show();
            }
        }

    }

