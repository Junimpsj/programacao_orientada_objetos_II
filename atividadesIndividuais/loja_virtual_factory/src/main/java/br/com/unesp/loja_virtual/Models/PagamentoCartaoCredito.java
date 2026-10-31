package br.com.unesp.loja_virtual.Models;

import br.com.unesp.loja_virtual.Interface.Pagamento;

/**
 * Pagamento via cartão de crédito.
 */
public class PagamentoCartaoCredito implements Pagamento {

    /**
     * {@inheritDoc}
     */
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$ " + valor + " realizado via cartão de crédito.");
    }
}