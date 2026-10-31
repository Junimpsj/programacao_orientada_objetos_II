package br.com.unesp.checkout_ecommerce.Aplication;

import br.com.unesp.checkout_ecommerce.Factory.BrasilCheckoutFactory;
import br.com.unesp.checkout_ecommerce.Factory.EuaCheckoutFactory;
import br.com.unesp.checkout_ecommerce.Models.Checkout;

/**
 * Ponto de entrada que demonstra o fechamento de pedidos no padrão Abstract Factory,
 * usando a família de componentes do Brasil e a dos EUA com os mesmos dados.
 */
public class App {

    /**
     * Fecha um pedido para cada país e imprime o resumo no console.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Checkout checkoutBrasil = new Checkout(new BrasilCheckoutFactory());
        checkoutBrasil.fecharPedido(100.0, 2.0);

        System.out.println();

        Checkout checkoutEua = new Checkout(new EuaCheckoutFactory());
        checkoutEua.fecharPedido(100.0, 2.0);
    }
}   
