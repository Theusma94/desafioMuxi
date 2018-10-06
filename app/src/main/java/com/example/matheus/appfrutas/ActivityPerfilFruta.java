package com.example.matheus.appfrutas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.appfrutas.interfaces.NativeCalcInterface;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

public class ActivityPerfilFruta extends AppCompatActivity implements NativeCalcInterface {

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

        // Necessário para receber os valores vindos pelo intent
        // do adapter FrutasAdapter
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        nomeFruta.setText(bundle.getString("nome"));
        precoDolar.setText("U$"+ bundle.getString("precodolar"));
        String url = bundle.getString("imagem");
        Picasso mPicasso = Picasso.get();

        // método para a checagem de onde o recurso (imagem) vem,
        // com o intuito de checar se o cache de imagens está sendo
        // feito corretamente
        mPicasso.setIndicatorsEnabled(true);

        mPicasso.get()
                .load(url)
                .resize(300, 300)
                .centerCrop()
                .into(imagemFruta);

        // Chama o método responsavel por criar uma nova instancia da classe
        // que fará a requisição ao código nativo
        CalculoAssincrono(bundle.getString("precodolar"));

    }

    private void CalculoAssincrono(String preco) {
        NativeCalc nativeCalc = new NativeCalc(this,this);
        nativeCalc.execute(""+preco);

    }

    // Implementação do método definido na interface NativeCalcInterface
    @Override
    public void devolvePrecoConvertido(String precoreal) {
        // Arredonda a String recebida da Classe NativeCalc
        // para duas casas decimais
        BigDecimal bigDecimal = new BigDecimal(precoreal);
        BigDecimal bigDecimal1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        precoReal.setText("R$"+ String.valueOf(bigDecimal1.doubleValue()));
    }
}
