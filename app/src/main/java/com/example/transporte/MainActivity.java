package com.example.transporte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    private static final String TAG="MainActivity";

    public static final String user="name";
    TextView txtUser;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.rutas);

        txtUser=(TextView)findViewById(R.id.txtEmail);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Transportes UTD (Concept)");
    }

    layout_rutas rutaFragment= new layout_rutas();
    layout_gps layout_gpsFragment = new layout_gps();
    layout_comentarios layoutComentariosFragment = new layout_comentarios();
    firebase_mapas firebasemapas = new firebase_mapas();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.rutas:
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, rutaFragment).commit();
            return true;

            case R.id.gps:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, layout_gpsFragment).commit();
                return true;

            case R.id.comentarios:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, layoutComentariosFragment).commit();
                return true;
        }
        return false;
    }
}



