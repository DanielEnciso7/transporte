package com.example.transporte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rutas_activity_verde extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta_azul);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Ruta verde");

        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase= FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference().child("Rutas").child("Ruta_verde");

    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Model, ViewHolder>(Model.class, R.layout.row,ViewHolder.class,mRef) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int i) {

                        viewHolder.setDetails(getApplicationContext(), model.getTitulo(),model.getDescripcion(),model.getImagen(),model.getCordenada1(), model.getCordenada2(), model.getCordenada3(), model.getCordenada4());

                        Toast.makeText(getApplicationContext(),model.getCordenada1(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder= super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {



                                TextView mcoordenada1Tv= view.findViewById(R.id.Tvcoordenada1);
                                TextView mcoordenada2Tv= view.findViewById(R.id.Tvcoordenada2);
                                TextView mcoordenada3Tv= view.findViewById(R.id.Tvcoordenada3);
                                TextView mcoordenada4Tv= view.findViewById(R.id.Tvcoordenada4);


                                String mcoordenada1= mcoordenada1Tv.getText().toString();
                                String mcoordenada2= mcoordenada2Tv.getText().toString();
                                String mcoordenada3= mcoordenada3Tv.getText().toString();
                                String mcoordenada4= mcoordenada4Tv.getText().toString();

                                Intent intent= new Intent(view.getContext(), Firebase_rutas.class);
                                intent.putExtra("cordenada1", mcoordenada1);
                                intent.putExtra("cordenada2", mcoordenada2);
                                intent.putExtra("cordenada3", mcoordenada3);
                                intent.putExtra("cordenada4", mcoordenada4);
                                startActivity(intent);



                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder;
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
