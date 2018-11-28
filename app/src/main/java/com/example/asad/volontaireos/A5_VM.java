package com.example.asad.volontaireos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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


public class A5_VM extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
     Button but;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a5_vmaps);



       but = (Button) findViewById(R.id.aaa);




       /*o
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent a = new Intent(A5_VM.this,Choice_Page.class);
                startActivity(a);
                finish();
                //return;
            }
        });*/



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("VolunteerAvailable");
        GeoFire geoFire = new GeoFire(ref);




        geoFire.setLocation(userId, new GeoLocation(27.1959532, 78.0245963), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if (error != null)
                    System.err.println("Error" + error);
                else
                    System.out.println("Location saved");
            }
        });



    }


/*

*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.a5_vmaps, container, false);
        final View button = view.findViewById(R.id.aaa);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // DO SOMETHING UPON THE CLICK
                        Intent a = new Intent(A5_VM.this,Choice_Page.class);
                        startActivity(a);
                        finish();
                    }
                }
        );
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Add marker in USM
        LatLng USM = new LatLng(5.35,100.30);
        mMap.addMarker(new MarkerOptions().position(USM).title("USM"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(USM));
    }



}
