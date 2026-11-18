package br.com.unesp.loja_virtual_decorator.Decorator;

import java.math.BigDecimal;

import br.com.unesp.loja_virtual_decorator.Interface.Produto;

/**
 * ConcreteDecorator: adiciona embalagem para presente (R$ 1,50) ao produto.
 */
public class EmbalagemPresente extends ProdutoDecorator {

    // BigDecimal com String pra não ter erro de ponto flutuante nos centavos
    private static final BigDecimal VALOR = new BigDecimal("1.50");

    /**
     * Cria o decorador de embalagem para presente.
     *
     * @param produto produto que receberá a embalagem
     */
    public EmbalagemPresente(Produto produto) {
        super(produto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Embalagem para presente";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getPreco() {
        return super.getPreco().add(VALOR);
    }
}
