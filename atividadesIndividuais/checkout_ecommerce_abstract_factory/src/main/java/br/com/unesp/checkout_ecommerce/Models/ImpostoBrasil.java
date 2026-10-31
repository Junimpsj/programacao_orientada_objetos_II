package br.com.unesp.checkout_ecommerce.Models;

import br.com.unesp.checkout_ecommerce.Interface.CalculadoraImposto;

/**
 * Imposto brasileiro: ICMS e tributos nacionais simplificados em 20% do valor do produto.
 */
public class ImpostoBrasil implements CalculadoraImposto {

    /**
     * Calcula o imposto brasileiro.
     *
     * @param valorProduto valor do produto, sem impostos
     * @return 20% do valor do produto
     */
    // ICMS + tributos nacionais simplificados em 20%
    @Override
    public double calcular(double valorProduto) {
        return valorProduto * 0.20;
    }
}
