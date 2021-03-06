package com.example.matheus.appfrutas.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.matheus.appfrutas.ActivityPerfilFruta;
import com.example.matheus.appfrutas.R;
import com.example.matheus.appfrutas.interfaces.RecyclerViewOnClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Matheus on 02/10/2018.
 */

public class FrutasAdapter extends RecyclerView.Adapter<FrutasAdapter.MyViewHolder> {
    private JSONArray jsonfruits;
    private Context ctx;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;
    public FrutasAdapter(Context ctx,JSONArray jsonfruits)
    {
        this.ctx = ctx;
        this.jsonfruits = jsonfruits;


    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new MyViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_frutas,parent,false));
    }

    @Override
    public void onBindViewHolder(FrutasAdapter.MyViewHolder holder, int position) {
            try {
                if(jsonfruits.getJSONObject(position)!=null) {
                    JSONObject fruits = jsonfruits.getJSONObject(position);
                    holder.nome.setText(fruits.getString("name"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



    }

    @Override
    public int getItemCount() {
        return jsonfruits.length();
    }



    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener r)
    {
        recyclerViewOnClickListener = r;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView nome;

        public MyViewHolder(View itemView){
            super(itemView);
            nome  = itemView.findViewById(R.id.nome_fruta);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(recyclerViewOnClickListener!=null)
            {
                recyclerViewOnClickListener.onClickListener(v,getAdapterPosition());
            }
        }
    }
}
