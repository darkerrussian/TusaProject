package com.example.tusaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tusaproject.interfaces.PassDataInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class CreateMapFragment extends Fragment {

    String location = "nothing";
    TextView textView;
    MarkerOptions markerOptions;
    LatLng myLatLng;
    Bundle bundle;
    PassDataInterface passDataInterface;
    public CreateMapFragment(PassDataInterface passDataInterface){
        this.passDataInterface = passDataInterface;

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_map, container,false);
        //Initialize map fragment


        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map_item_create);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        googleMap.clear();

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));


                        googleMap.addMarker(markerOptions);

                        location = markerOptions.getTitle();

                        passDataInterface.onDataReceived(location);




                        bundle = new Bundle();



                        bundle.putString("output_location", location);


                        supportMapFragment.setArguments(bundle);






                    }
                });
            }
        });





        return view;
    }


}