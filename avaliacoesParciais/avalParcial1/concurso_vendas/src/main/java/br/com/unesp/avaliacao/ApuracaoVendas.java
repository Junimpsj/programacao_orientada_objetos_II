package br.com.unesp.avaliacao;

import java.util.ArrayList;

public class ApuracaoVendas {

    private ArrayList<Venda> vendas;
    private int qtddVendas;
    private Venda maiorVenda;
    private Venda ultimaVenda;

    public ApuracaoVendas() {
        
        this.qtddVendas = 0;
        this.vendas = new ArrayList<>();

        this.maiorVenda = null;
        this.ultimaVenda = null;
    }

    public void adicionarVenda(Venda venda) {

        if(getQtddVendas() < 10) { 

            if(venda.getParticipante().getCategoria() == 2)  {

                venda.setValorVenda(venda.getValorVenda()*1.25);
            }

            vendas.add(venda);

            ultimaVenda = venda;

            if(maiorVenda != null) {
                if(eMaiorVenda(venda)) {
                    this.maiorVenda = venda;
                }
            } else {
                this.maiorVenda = venda;
            }
            

            this.qtddVendas = this.qtddVendas+1;

        } else {
            
            throw new IllegalStateException("Máximo de vendas atingido (10).");
        }

    }

    public boolean eMaiorVenda(Venda venda) {

        if(maiorVenda.getValorVenda() < venda.getValorVenda()) {
            return true;
        }
        return false;
    }

    public int getQtddVendas()  {

        return this.qtddVendas;
    }
}