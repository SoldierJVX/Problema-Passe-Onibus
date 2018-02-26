/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemapasseonibus;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author SoldierJVX
 */
class SistemaPassagem {

    private ArrayList<ValorPassagem> valoresPassagem;
    private ArrayList<String> textos;
    private JList lstTela;

    public SistemaPassagem(ValorPassagem valorPassagem, JList lstTela) {
        this.valoresPassagem = new ArrayList<>();
        this.valoresPassagem.add(valorPassagem);
        this.lstTela = lstTela;
        textos = new ArrayList<>();
    }

    public void novoValor(ValorPassagem valorPassagem) {
        this.valoresPassagem.add(valorPassagem);
    }

    public void cobrarPassagem(Cartao card) {

        ArrayList<Carga> cargasCartao = card.usarCreditos();
        ValorPassagem possuiSaldo;

        //Checar se possui creditos o suficiente
        possuiSaldo = checarSaldo(cargasCartao, valoresPassagem);

        if (possuiSaldo == null) {
            passagemUsada(false);
            return;
        }

        //Buscar os creditos e descontar do cartão
        descontarCartao(cargasCartao, possuiSaldo, card);

        passagemUsada(true);
        return;

    }

    private ValorPassagem checarSaldo(ArrayList<Carga> cargasCartao, ArrayList<ValorPassagem> valoresPassagem) {

        double cargasNasFaixas[] = new double[valoresPassagem.size()];

        if (valoresPassagem.size() > 1) {

            for (int i = 0; i < cargasCartao.size(); i++) {

                for (int j = 0; j < valoresPassagem.size(); j++) {
                    Date dataCarga = cargasCartao.get(i).getData();
                    Date dataPassagem = valoresPassagem.get(j).getData();
                    Date dataProxPassagem;

                    if (j < valoresPassagem.size() - 1) {
                        dataProxPassagem = valoresPassagem.get(j + 1).getData();
                    } else {
                        dataProxPassagem = null;
                    }

                    if (dataCarga.after(dataPassagem) && dataProxPassagem != null && dataCarga.before(dataProxPassagem)) {
                        cargasNasFaixas[j] += cargasCartao.get(i).getValor();
                        break;
                    } else if (dataProxPassagem == null) {
                        cargasNasFaixas[j] += cargasCartao.get(i).getValor();
                    }

                }

            }
            
            for (int i = 0; i < cargasNasFaixas.length; i++) {
                if(cargasNasFaixas[i] >= valoresPassagem.get(i).getValorPassagem()){
                    return valoresPassagem.get(i);
                }
            }
            
            return null;

        }else{
            
            double soma = 0;
            
            for (int i = 0; i < cargasCartao.size(); i++) {
                soma += cargasCartao.get(i).getValor();
            }
            
            if(soma >= valoresPassagem.get(0).getValorPassagem()){
                return valoresPassagem.get(0);
            }
        }

        return null;

    }

    private void descontarCartao(ArrayList<Carga> cargasCartao, ValorPassagem valorPassagem, Cartao card ) {

        double valorPassagemRestante = valorPassagem.getValorPassagem();
        
        for (int i = 0; i < cargasCartao.size(); i++) {
            double valor = cargasCartao.get(i).getValor();
            Date data = cargasCartao.get(i).getData();

            if(data.after(valorPassagem.getData())){
                if(valor > valorPassagemRestante){
                    card.usarCreditos().get(i).usarCarga(valorPassagemRestante);
                    return;
                }else if(valor == valorPassagemRestante){
                    card.usarCreditos().remove(i);
                    return;
                }else{
                    valorPassagemRestante -= card.usarCreditos().get(i).getValor();
                    card.usarCreditos().remove(i);
                }
            }

        }

    }

    private void passagemUsada(boolean status) {
        
        DefaultListModel model = new DefaultListModel();
        
        if (status) {
            textos.add("Passagem liberada");
            
        } else {
            textos.add("Sem créditos");
        }
        
        for (int i = 0; i < textos.size(); i++) {
            model.addElement(textos.get(i));
        }
        
        lstTela.setModel(model);
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(SistemaPassagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
