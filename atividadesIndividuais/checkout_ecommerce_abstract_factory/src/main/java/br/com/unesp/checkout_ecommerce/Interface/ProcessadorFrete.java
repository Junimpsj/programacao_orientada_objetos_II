package br.com.unesp.checkout_ecommerce.Interface;

/**
 * Produto abstrato que calcula o frete de um pedido.
 */
public interface ProcessadorFrete {
    /**
     * Calcula o frete de um pedido.
     *
     * @param pesoKg peso do pedido em quilogramas
     * @return o valor do frete
     */
    double calcularFrete(double pesoKg);
}
