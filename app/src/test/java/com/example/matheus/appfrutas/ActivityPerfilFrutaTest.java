package com.example.matheus.appfrutas;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Matheus on 07/10/2018.
 * Classe com o intuito de testar se, ao setar o numero de casas decimais,
 * o parametro BigDecimal.ROUND_HALF_UP cumpra com o que esperado que é
 * arrendondar a segunda casa decimal somando 1 caso a terceira casa seja >=5
 */
public class ActivityPerfilFrutaTest {
    @Test
    public void arredondaParaCima() throws Exception {
        //Teste para verificar se ,o numero após as duas casas decimais for >= 5
        // limita o numero de casas decimais arredondando a ultima casa com +1
        // o caso minimo é na terceira casa sendo 5
        ActivityPerfilFruta perfilFruta = new ActivityPerfilFruta();
        Assert.assertEquals("15.3",perfilFruta.devolvePrecoConvertidoteste("15.295"));
    }

    @Test
    public void arredondaParaBaixo() throws Exception {
        //Teste para verificar se ,o numero após as duas casas decimais for < 5
        // limita o numero de casas decimais mantendo os numero
        // o caso minimo é na terceira casa sendo 4. Sendo 5 a segunda casa decimal sofre alteração
        ActivityPerfilFruta perfilFruta = new ActivityPerfilFruta();
        Assert.assertEquals("15.25",perfilFruta.devolvePrecoConvertidoteste("15.252"));
    }

}