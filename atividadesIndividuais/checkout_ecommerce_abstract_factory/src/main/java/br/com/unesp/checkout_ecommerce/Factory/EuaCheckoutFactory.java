package br.com.unesp.checkout_ecommerce.Factory;

import br.com.unesp.checkout_ecommerce.Interface.CalculadoraImposto;
import br.com.unesp.checkout_ecommerce.Interface.ProcessadorFrete;
import br.com.unesp.checkout_ecommerce.Models.FreteEUA;
import br.com.unesp.checkout_ecommerce.Models.ImpostoEUA;

/**
 * Fábrica concreta do Abstract Factory que produz os componentes de checkout dos EUA
 * (imposto e frete americanos).
 */
public class EuaCheckoutFactory implements PaisCheckoutFactory {

    /**
     * Cria a calculadora de imposto dos EUA.
     *
     * @return uma {@link ImpostoEUA}
     */
    @Override
    public CalculadoraImposto criarCalculadoraImposto() {
        return new ImpostoEUA();
    }

    /**
     * Cria o processador de frete dos EUA.
     *
     * @return um {@link FreteEUA}
     */
    @Override
    public ProcessadorFrete criarProcessadorFrete() {
        return new FreteEUA();
    }
}
