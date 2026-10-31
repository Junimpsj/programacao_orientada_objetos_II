package br.com.unesp.loja_virtual.Models;

import br.com.unesp.loja_virtual.Interface.Pagamento;

/**
 * Pagamento via PIX.
 */
public class PagamentoPix implements Pagamento {

    /**
     * {@inheritDoc}
     */
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$ " + valor + " realizado via PIX.");
    }
}