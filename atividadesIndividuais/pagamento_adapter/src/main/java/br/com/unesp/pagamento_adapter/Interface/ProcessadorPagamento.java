package br.com.unesp.pagamento_adapter.Interface;

/**
 * Target do Adapter: contrato de pagamento que o sistema de vendas já conhece.
 */
// target, contrato que o sistema de vendas já conhece
public interface ProcessadorPagamento {
    /**
     * Realiza um pagamento.
     *
     * @param valor valor do pagamento em reais
     */
    void realizarPagamento(double valor);
    /**
     * Cancela um pagamento já realizado.
     *
     * @param idTransacao identificador da transação a ser cancelada
     */
    void cancelarPagamento(String idTransacao);
}
