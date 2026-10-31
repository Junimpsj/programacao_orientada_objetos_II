package br.com.unesp.loja_virtual.Models;

import br.com.unesp.loja_virtual.Interface.Pagamento;

/**
 * Pagamento via boleto.
 */
public class PagamentoBoleto implements Pagamento {

    /**
     * {@inheritDoc}
     */
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$ " + valor + " realizado via boleto.");
    }
}