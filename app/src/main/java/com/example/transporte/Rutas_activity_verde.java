package com.example.transporte;

import android.os.Bundle;

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
        setContentView(R.layout.activity_ruta_verde);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Post List");

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

                        viewHolder.setDetails(getApplicationContext(), model.getTitulo(),model.getDescripcion(),model.getImagen());
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
