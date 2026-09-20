package br.com.unesp.compras_mercado.Model;

public class PagamentoCheque extends Pagamento {
    
    @Override
    public String metodoPagamento() {

        return "Cheque";

    }

}