package br.com.unesp.checkout_ecommerce.Models;

import br.com.unesp.checkout_ecommerce.Interface.ProcessadorFrete;

/**
 * Frete americano: taxa fixa de $ 10,00 mais $ 3,00 por quilograma.
 */
public class FreteEUA implements ProcessadorFrete {

    /**
     * Calcula o frete americano.
     *
     * @param pesoKg peso do pedido em quilogramas
     * @return o valor do frete em dólares
     */
    // taxa fixa de $ 10,00 + $ 3,00 por kg
    @Override
    public double calcularFrete(double pesoKg) {
        return 10.00 + (pesoKg * 3.00);
    }
}
