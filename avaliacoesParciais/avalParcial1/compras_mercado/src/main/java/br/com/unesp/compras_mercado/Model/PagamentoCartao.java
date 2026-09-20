package br.com.unesp.compras_mercado.Model;

public class PagamentoCartao extends Pagamento {
    
    @Override
    public String metodoPagamento() {

        return "Cartão";

    }

}