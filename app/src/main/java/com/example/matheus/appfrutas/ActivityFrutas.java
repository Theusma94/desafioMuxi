package com.example.matheus.appfrutas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matheus.appfrutas.adapters.FrutasAdapter;
import com.example.matheus.appfrutas.interfaces.RecyclerViewOnClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityFrutas extends AppCompatActivity implements RecyclerViewOnClickListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RequestQueue mQueue;
    private JSONArray jsonArray = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frutas);
        // Cria uma nova requisição utilizando o framework Volley
        mQueue = Volley.newRequestQueue(this);

        //Configurações do recyclerview e layoutmanager
        recyclerView =  findViewById(R.id.frutas);
        recyclerView.setHasFixedSize(true); //significa que o tamanho do RecyclerView não vai mudar
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        consomeJson();

    }

    public void consomeJson(){
        String url = "https://raw.githubusercontent.com/muxidev/desafio-android/master/fruits.json";
        //Cria a requisição do tipo GET
        JsonObjectRequest  request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    /*Caso a busca seja feita com sucesso
                    * é retirado do JSONObject o array de nome 'fruits'
                    * que é o unico presente na url
                    */
                     jsonArray = response.getJSONArray("fruits");

                     /*Cria-se um adapter com passando por parametro o contexto e o JSONArray obtido da url
                     * em seguida o adapter é setado no recyclerView
                      */
                     FrutasAdapter adapter = new FrutasAdapter(ActivityFrutas.this,jsonArray);
                     adapter.setRecyclerViewOnClickListener(ActivityFrutas.this);
                     recyclerView.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }

    @Override
    public void onClickListener(View view, int position) {

        FrutasAdapter adapter = (FrutasAdapter) recyclerView.getAdapter();
        adapter.exibeFruta(view,position);

    }
}
