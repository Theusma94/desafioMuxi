package com.example.matheus.appfrutas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ActivityPerfilFruta extends AppCompatActivity {

    private ImageView imagemFruta;
    private TextView nomeFruta;
    private TextView precoDolar;
    private TextView precoReal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_fruta);

        imagemFruta = findViewById(R.id.imagemItemFruta);
        nomeFruta = findViewById(R.id.nomeItemFruta);
        precoDolar = findViewById(R.id.precoDolar);
        precoReal = findViewById(R.id.precoReal);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        nomeFruta.setText(bundle.getString("nome"));
        precoDolar.setText("$"+ bundle.getString("precodolar"));
        String url = bundle.getString("imagem");
        Picasso mPicasso = Picasso.get();
        mPicasso.setIndicatorsEnabled(true);
        mPicasso.get()
                .load(url)
                .resize(300, 300)
                .centerCrop()
                .into(imagemFruta);
    }
}
