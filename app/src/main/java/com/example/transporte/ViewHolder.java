package com.example.transporte;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(View itemView)
    {
        super(itemView);

        mView=itemView;
    }

    public void setDetails(Context ctx, String titulo, String descripcion, String imagen)
    {
        TextView mTituloView= mView.findViewById(R.id.tituloTv);
        TextView mDescripcionView= mView.findViewById(R.id.descripcionTv);
        ImageView mimagenView= mView.findViewById(R.id.imagenTv);

        mTituloView.setText(titulo);
        mDescripcionView.setText(descripcion);
        Picasso.get().load(imagen).into(mimagenView);

    }
}
