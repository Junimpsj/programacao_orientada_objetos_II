package br.com.unesp.checkout_ecommerce.Factory;

import br.com.unesp.checkout_ecommerce.Interface.CalculadoraImposto;
import br.com.unesp.checkout_ecommerce.Interface.ProcessadorFrete;

/**
 * Fábrica abstrata do checkout: cada país fornece a sua família de componentes
 * (imposto e frete), garantindo que eles sempre sejam compatíveis entre si.
 */
// abstract factory, cada país implementa e devolve a sua família de componentes (imposto + frete)
public interface PaisCheckoutFactory {
    /**
     * Cria a calculadora de imposto do país.
     *
     * @return a calculadora de imposto do país
     */
    CalculadoraImposto criarCalculadoraImposto();
    /**
     * Cria o processador de frete do país.
     *
     * @return o processador de frete do país
     */
    ProcessadorFrete criarProcessadorFrete();
}
