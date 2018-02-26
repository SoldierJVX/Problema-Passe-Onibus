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
public class Transacao {

    private double valor;
    private Date data;

    public Transacao(double valor, Date data) {
        this.valor = valor;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
