package br.com.unesp.checkout_ecommerce.Models;

import br.com.unesp.checkout_ecommerce.Interface.CalculadoraImposto;

/**
 * Imposto americano: sales tax médio de 7% do valor do produto.
 */
public class ImpostoEUA implements CalculadoraImposto {

    /**
     * Calcula o imposto americano.
     *
     * @param valorProduto valor do produto, sem impostos
     * @return 7% do valor do produto
     */
    // sales tax médio de 7%
    @Override
    public double calcular(double valorProduto) {
        return valorProduto * 0.07;
    }
}
