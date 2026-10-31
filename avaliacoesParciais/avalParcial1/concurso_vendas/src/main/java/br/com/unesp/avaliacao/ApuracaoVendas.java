package br.com.unesp.avaliacao;

import java.util.ArrayList;

/**
 * Apuração das vendas do concurso: limite de 10 vendas, bônus de 25% para
 * participantes sêniores e controle da maior e da última venda.
 */
public class ApuracaoVendas {

    /**
     * Vendas registradas.
     */
    private ArrayList<Venda> vendas;
    /**
     * Quantidade de vendas registradas.
     */
    private int qtddVendas;
    /**
     * Venda de maior valor.
     */
    private Venda maiorVenda;
    /**
     * Última venda registrada.
     */
    private Venda ultimaVenda;

    /**
     * Cria uma apuração sem vendas.
     */
    public ApuracaoVendas() {
        
        this.qtddVendas = 0;
        this.vendas = new ArrayList<>();

        this.maiorVenda = null;
        this.ultimaVenda = null;
    }

    /**
     * Registra uma venda, aplicando +25% ao valor se o participante for sênior.
     *
     * @param venda venda a registrar
     * @throws IllegalStateException se o máximo de 10 vendas já foi atingido
     */
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

    /**
     * Verifica se a venda supera a maior venda registrada.
     *
     * @param venda venda a comparar
     * @return {@code true} se o valor da venda for maior que o da maior venda atual
     */
    public boolean eMaiorVenda(Venda venda) {

        if(maiorVenda.getValorVenda() < venda.getValorVenda()) {
            return true;
        }
        return false;
    }

    /**
     * Retorna a quantidade de vendas registradas.
     *
     * @return a quantidade de vendas
     */
    public int getQtddVendas()  {

        return this.qtddVendas;
    }
}