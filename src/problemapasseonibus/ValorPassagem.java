/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemapasseonibus;

import java.util.Date;

/**
 *
 * @author SoldierJVX
 */
public class ValorPassagem extends Transacao {

    public ValorPassagem(double valor, Date data) {
        super(valor, data);
    }

    public double getValorPassagem() {
        return super.getValor();
    }

    public Date getData() {
        return super.getData();
    }

}
