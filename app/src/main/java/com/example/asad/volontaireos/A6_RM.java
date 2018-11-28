package com.example.asad.volontaireos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class A6_RM extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private Button mLogout , mRequest ;
    private LatLng pickupLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a7_rmaps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("VolunteerAvailable");
        GeoFire geoFire = new GeoFire(ref);

        mLogout=(Button) findViewById(R.id.logouttayub);

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent a = new Intent(A6_RM.this,A1_Main.class);
                startActivity(a);
                finish();
                return;
            }
        });

        mRequest = (Button) findViewById(R.id.request);

        mRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CustomerRequest");

                GeoFire geoFire = new GeoFire(ref);
                geoFire.setLocation(userId, new GeoLocation(5.3342641, 100.3066604), new GeoFire.CompletionListener() {
                    @Override
                    public void onComplete(String key, DatabaseError error) {
                        if(error!=null)
                            System.err.println("Error"+ error);
                        else
                            System.out.println("Location saved");
                    }
                });

                pickupLocation = new LatLng(5.3342641,100.3066604);
                mMap.addMarker(new MarkerOptions().position(pickupLocation).title("Pickup Here"));
                mRequest.setText("Gettin your Driver");

            }
        });


        geoFire.setLocation(userId, new GeoLocation(5.3342641, 100.3066604), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if(error!=null)
                    System.err.println("Error"+ error);
                else
                    System.out.println("Location saved");
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Add marker in USM
        LatLng QB = new LatLng(5.3342641,100.3066604);
        mMap.addMarker(new MarkerOptions().position(QB).title("Queensbay"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(QB));

    }
}
