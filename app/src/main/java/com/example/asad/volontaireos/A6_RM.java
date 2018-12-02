package com.example.asad.volontaireos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
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
    private Button mLogout  ;
    private LatLng pickupLocation;

    private Button mMakeRequest , mViewHistory ;
    private Switch Display_Button ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a7_rmaps);

        mMakeRequest = (Button) findViewById(R.id.MakeRequest);
        mViewHistory = (Button) findViewById(R.id.ViewHistory);
        Display_Button = (Switch) findViewById(R.id.Display_Buttons);



        mMakeRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent a = new Intent(A6_RM.this,A11_Rmakereq.class);
                Intent a = new Intent(A6_RM.this,Form.class);
                startActivity(a);
                finish();
                return;
            }
        });



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("VolunteerAvailable");
        final GeoFire geoFire = new GeoFire(ref);

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






        geoFire.setLocation(userId, new GeoLocation(5.3342641, 100.3066604), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if(error!=null)
                    System.err.println("Error"+ error);
                else
                    System.out.println("Location saved");
            }
        });


        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 12.0f));

                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CustomerRequest");

                geoFire.setLocation(userId, new
                        GeoLocation(place.getLatLng().latitude, place.getLatLng().longitude), new
                        GeoFire.CompletionListener() {
                            @Override

                            public void onComplete(String key, DatabaseError error) {
                                if (error != null) {
                                    Toast.makeText(A6_RM.this, "There was an error saving the location to GeoFire: " + error, Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(A6_RM.this, "Location saved on server successfully!", Toast.LENGTH_LONG).show();

                                }
                            }
                        });

            }


            @Override
            public void onError(Status status) {

            }
        });




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Add marker in QB
        LatLng QB = new LatLng(5.3342641,100.3066604);
        mMap.addMarker(new MarkerOptions().position(QB).title("Queensbay"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(QB));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));

    }
}
