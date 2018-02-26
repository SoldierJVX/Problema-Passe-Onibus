/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemapasseonibus;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author SoldierJVX
 */
public class Cartao {
    
    private ArrayList<Carga> cargas;
    
    public Cartao(){
        this.cargas = new ArrayList();
    }
    
    public void carregarCreditos(double valor, Date data){
        this.cargas.add(new Carga(valor, data));
    }
    
    public ArrayList<Carga> usarCreditos(){
        return this.cargas;
    }
    
}
