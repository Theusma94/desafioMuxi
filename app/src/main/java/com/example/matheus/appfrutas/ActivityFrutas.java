package com.example.matheus.appfrutas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.matheus.appfrutas.adapters.FrutasAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityFrutas extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<String> content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frutas);
        content = new ArrayList<>();
        for (int i = 0; i<12;i++){
            content.add("item"+(i+1));
        }


        recyclerView =  findViewById(R.id.frutas);
        recyclerView.setHasFixedSize(true); //significa que o tamanho do RecyclerView não vai mudar
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FrutasAdapter adapter = new FrutasAdapter(this,content);
        recyclerView.setAdapter(adapter);
    }
}
