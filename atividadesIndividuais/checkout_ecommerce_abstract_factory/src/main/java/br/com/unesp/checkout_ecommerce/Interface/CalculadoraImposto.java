package br.com.unesp.checkout_ecommerce.Interface;

/**
 * Produto abstrato que calcula o imposto incidente sobre um produto.
 */
public interface CalculadoraImposto {
    /**
     * Calcula o imposto de um produto.
     *
     * @param valorProduto valor do produto, sem impostos
     * @return o valor do imposto
     */
    double calcular(double valorProduto);
}
