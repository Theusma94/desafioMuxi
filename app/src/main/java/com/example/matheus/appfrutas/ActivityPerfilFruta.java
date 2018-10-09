package com.example.matheus.appfrutas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
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
    private Toolbar toolbar;
    private ViewGroup mRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Transições
        // Api minima deve ser 21 (Lollipop)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            getWindow().setSharedElementEnterTransition(new ChangeBounds());

            Transition transition = getWindow().getSharedElementEnterTransition();
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                // Api minima 19
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onTransitionEnd(Transition transition) {
                    // Após a transição entre activities completa
                    // é necessário iniciar a animação dos TextView do preço
                    Slide trans = new Slide();
                    trans.setDuration(700);
                    TransitionManager.beginDelayedTransition(mRoot,trans);
                    precoDolar.setVisibility(View.VISIBLE);
                    precoReal.setVisibility(View.VISIBLE);
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_fruta);

        imagemFruta = findViewById(R.id.imagemItemFruta);
        nomeFruta = findViewById(R.id.nomeItemFruta);
        precoDolar = findViewById(R.id.precoDolar);
        precoReal = findViewById(R.id.precoReal);
        mRoot = (ViewGroup) findViewById(R.id.layoutPerfil);

        // Necessário para receber os valores vindos pelo intent
        // do adapter FrutasAdapter
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        nomeFruta.setText(bundle.getString("nome"));
        precoDolar.setText("U$ " + bundle.getString("precodolar"));
        String url = bundle.getString("imagem");

        //Configurando o toolbar
        toolbar = findViewById(R.id.toolbarPerfilFruta);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Slide trans = new Slide();
                    trans.setDuration(300);
                    TransitionManager.beginDelayedTransition(mRoot,trans);
                    precoReal.setVisibility(View.INVISIBLE);
                    precoDolar.setVisibility(View.INVISIBLE);
                }
                onBackPressed();
            }
        });



        Picasso mPicasso = Picasso.get();

        // método para a checagem de onde o recurso (imagem) vem,
        // com o intuito de checar se o cache de imagens está sendo
        // feito corretamente
        //mPicasso.setIndicatorsEnabled(true);

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
        //Seta o numero de casas decimais em duas e arredonda a segunda casa decimal com +1 caso a terceira casa for >=5
        BigDecimal bigDecimal1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        precoReal.setText("R$ "+ String.valueOf(bigDecimal1.doubleValue()));
    }

    // Método com finalidades de teste
    // Similar ao devolvePrecoConvertido porem retornando a string
    public String devolvePrecoConvertidoteste(String precoreal) {
        BigDecimal bigDecimal = new BigDecimal(precoreal);
        BigDecimal bigDecimal1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(bigDecimal1.doubleValue());

    }

    // Necessário para configurar a animação do textview no botao de voltar do próprio dispositivo
    @Override
    public void onBackPressed(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Slide trans = new Slide();
            trans.setDuration(300);
            TransitionManager.beginDelayedTransition(mRoot,trans);
            precoDolar.setVisibility(View.INVISIBLE);
            precoReal.setVisibility(View.INVISIBLE);
        }
        super.onBackPressed();
    }

}
