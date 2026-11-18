package br.com.unesp.loja_virtual_decorator.Decorator;

import java.math.BigDecimal;

import br.com.unesp.loja_virtual_decorator.Interface.Produto;

/**
 * ConcreteDecorator: adiciona garantia estendida (R$ 30,00) ao produto.
 */
public class GarantiaEstendida extends ProdutoDecorator {

    private static final BigDecimal VALOR = new BigDecimal("30.00");

    /**
     * Cria o decorador de garantia estendida.
     *
     * @param produto produto que receberá a garantia
     */
    public GarantiaEstendida(Produto produto) {
        super(produto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Garantia estendida";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getPreco() {
        return super.getPreco().add(VALOR);
    }
}
