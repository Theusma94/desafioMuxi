package com.example.matheus.appfrutas;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.matheus.appfrutas.interfaces.NativeCalcInterface;

/**
 * Created by Matheus on 05/10/2018.
 */

public class NativeCalc extends AsyncTask<String,String,String> {
    private Context ctx;
    private NativeCalcInterface nativeCalcInterface;
    public native float converteDolar(float preco);
    static{
        System.loadLibrary("convertepreco");
    }


    public NativeCalc(Context ctx, NativeCalcInterface nativeCalcInterface){
        this.ctx = ctx;
        this.nativeCalcInterface = nativeCalcInterface;
    }

    // Método que chama assincronamente o arquivo convertepreco.c
    @Override
    protected String doInBackground(String... strings) {
        Float tofloat = Float.parseFloat(strings[0]);
        Float retorno = converteDolar(tofloat);

        return String.valueOf(retorno);
    }

    // Recebe o valor do método doInBackground e chama o método
    // devolvePrecoConvertido presente na Classe ActivityPerfilFruta
    // onde o mesmo implementa o método
    @Override
    protected void onPostExecute(String s) {
        nativeCalcInterface.devolvePrecoConvertido(s);
    }
}
