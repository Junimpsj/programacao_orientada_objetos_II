package br.com.unesp.loja_virtual_decorator.Decorator;

import java.math.BigDecimal;

import br.com.unesp.loja_virtual_decorator.Interface.Produto;

/**
 * ConcreteDecorator: adiciona seguro contra danos (R$ 20,00) ao produto.
 */
public class SeguroContraDanos extends ProdutoDecorator {

    private static final BigDecimal VALOR = new BigDecimal("20.00");

    /**
     * Cria o decorador de seguro contra danos.
     *
     * @param produto produto que receberá o seguro
     */
    public SeguroContraDanos(Produto produto) {
        super(produto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Seguro contra danos";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getPreco() {
        return super.getPreco().add(VALOR);
    }
}
