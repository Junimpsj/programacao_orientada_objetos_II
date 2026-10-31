package br.com.unesp.compras_mercado.Model;

/**
 * Pagamento com cheque.
 */
public class PagamentoCheque extends Pagamento {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String metodoPagamento() {

        return "Cheque";

    }

}