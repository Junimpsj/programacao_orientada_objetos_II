package br.com.unesp.checkout_ecommerce.Models;

import br.com.unesp.checkout_ecommerce.Interface.ProcessadorFrete;

/**
 * Frete brasileiro: taxa fixa de R$ 15,00 mais R$ 5,00 por quilograma.
 */
public class FreteBrasil implements ProcessadorFrete {

    /**
     * Calcula o frete brasileiro.
     *
     * @param pesoKg peso do pedido em quilogramas
     * @return o valor do frete em reais
     */
    // taxa fixa de R$ 15,00 + R$ 5,00 por kg
    @Override
    public double calcularFrete(double pesoKg) {
        return 15.00 + (pesoKg * 5.00);
    }
}
