package br.com.unesp.compras_mercado.Model;

/**
 * Pagamento com cartão.
 */
public class PagamentoCartao extends Pagamento {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String metodoPagamento() {

        return "Cartão";

    }

}