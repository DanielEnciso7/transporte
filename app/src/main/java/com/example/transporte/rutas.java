package com.example.transporte;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class rutas extends Fragment implements View.OnClickListener{


    public rutas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rutas, container, false);

        CardView card_azul = v.findViewById(R.id.cardViewAzul);
        card_azul.setOnClickListener(this);

        CardView card_verde = v.findViewById(R.id.cardViewVerde);
        card_verde.setOnClickListener(this);
        return v;


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardViewAzul:

                Intent intent1 = new Intent(getActivity(), Rutas_activity_azul.class);
                startActivity(intent1);

                break;

            case R.id.cardViewVerde:

                Intent intent2 = new Intent(getActivity(), Rutas_activity_verde.class);
                startActivity(intent2);

                break;
        }
    }
}
