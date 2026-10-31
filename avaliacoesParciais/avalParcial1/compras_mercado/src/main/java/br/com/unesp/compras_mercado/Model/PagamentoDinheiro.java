package br.com.unesp.compras_mercado.Model;

/**
 * Pagamento em dinheiro.
 */
public class PagamentoDinheiro extends Pagamento {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String metodoPagamento() {

        return "Dinheiro";

    }

}