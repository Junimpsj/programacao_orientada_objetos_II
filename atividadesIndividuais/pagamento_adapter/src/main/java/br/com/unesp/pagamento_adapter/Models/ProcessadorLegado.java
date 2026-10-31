package br.com.unesp.pagamento_adapter.Models;

import br.com.unesp.pagamento_adapter.Interface.ProcessadorPagamento;

/**
 * Processador de pagamento do sistema legado, que já respeita o contrato do sistema.
 */
public class ProcessadorLegado implements ProcessadorPagamento {

    /**
     * {@inheritDoc}
     */
    @Override
    public void realizarPagamento(double valor) {
        System.out.println("[Legado] Processando pagamento de R$ " + valor + " via sistema legado.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelarPagamento(String idTransacao) {
        System.out.println("[Legado] Cancelando transação " + idTransacao + " no sistema legado.");
    }
}
