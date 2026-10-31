package br.com.unesp.checkout_ecommerce.Factory;

import br.com.unesp.checkout_ecommerce.Interface.CalculadoraImposto;
import br.com.unesp.checkout_ecommerce.Interface.ProcessadorFrete;
import br.com.unesp.checkout_ecommerce.Models.FreteBrasil;
import br.com.unesp.checkout_ecommerce.Models.ImpostoBrasil;

/**
 * Fábrica concreta do Abstract Factory que produz os componentes de checkout do Brasil
 * (imposto e frete brasileiros).
 */
public class BrasilCheckoutFactory implements PaisCheckoutFactory {

    /**
     * Cria a calculadora de imposto do Brasil.
     *
     * @return uma {@link ImpostoBrasil}
     */
    @Override
    public CalculadoraImposto criarCalculadoraImposto() {
        return new ImpostoBrasil();
    }

    /**
     * Cria o processador de frete do Brasil.
     *
     * @return um {@link FreteBrasil}
     */
    @Override
    public ProcessadorFrete criarProcessadorFrete() {
        return new FreteBrasil();
    }
}
