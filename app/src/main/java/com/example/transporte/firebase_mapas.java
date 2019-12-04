package com.example.transporte;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transporte.directionhelpers.FetchURL;
import com.example.transporte.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class firebase_mapas extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {

    Button ButtonGetDirection;
    GoogleMap map;
    MarkerOptions lugar1, lugar2;
    LatLng latLng1, latLng2;
    Polyline currentPolyline;
    String coordenada1, coordenada2, coordenada3, coordenada4 ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_firebase_get_rutas);


        ActionBar actionBar= getSupportActionBar();

        actionBar.setTitle("Mapa");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        coordenada1= getIntent().getStringExtra("cordenada1");
        coordenada2= getIntent().getStringExtra("cordenada2");
        coordenada3= getIntent().getStringExtra("cordenada3");
        coordenada4= getIntent().getStringExtra("cordenada4");

        double cord1 = Double.parseDouble(coordenada1);
        double cord2 = Double.parseDouble(coordenada2);
        double cord3 = Double.parseDouble(coordenada3);
        double cord4 = Double.parseDouble(coordenada4);


        MapFragment mapFragment= (MapFragment) getFragmentManager().findFragmentById(R.id.mapFrag);
        mapFragment.getMapAsync(this);


        lugar1= new MarkerOptions().position(new LatLng(cord1, cord2)).title("Locación 1");
        lugar2= new MarkerOptions().position(new LatLng(cord3, cord4)).title("Locación 2");


        String url = getUrl(lugar1.getPosition(), lugar2.getPosition(), "driving");
        new FetchURL(firebase_mapas.this).execute(url, "driving");



    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map= googleMap;

        latLng1 = new LatLng(24.001250, -104.648480);
        map.addMarker(lugar1);
        map.addMarker(lugar2);

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(lugar1.getPosition(), 14), 100, null);

    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if(currentPolyline !=null)
            currentPolyline.remove();
        currentPolyline=map.addPolyline((PolylineOptions) values[0]);


    }
}
