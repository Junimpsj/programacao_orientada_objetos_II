package br.com.unesp.loja_virtual.Interface;

/**
 * Produto da Factory: uma forma de pagamento da loja.
 */
public interface Pagamento {
    /**
     * Realiza o pagamento.
     *
     * @param valor valor a ser pago, em reais
     */
    void pagar(double valor);
}