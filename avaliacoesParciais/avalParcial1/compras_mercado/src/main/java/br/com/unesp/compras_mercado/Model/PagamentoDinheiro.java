package br.com.unesp.compras_mercado.Model;

public class PagamentoDinheiro extends Pagamento {
    
    @Override
    public String metodoPagamento() {

        return "Dinheiro";

    }

}