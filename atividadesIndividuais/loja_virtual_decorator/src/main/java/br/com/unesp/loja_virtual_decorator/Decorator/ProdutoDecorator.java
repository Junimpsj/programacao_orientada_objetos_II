package br.com.unesp.loja_virtual_decorator.Decorator;

import java.math.BigDecimal;

import br.com.unesp.loja_virtual_decorator.Interface.Produto;

/**
 * Decorator: implementa {@link Produto} e mantém uma referência a outro produto,
 * delegando a ele a descrição e o preço.
 */
// decorator, implementa Produto e embrulha outro Produto, então dá pra empilhar serviços em qualquer ordem
// sem mexer no ProdutoBasico e sem criar uma classe pra cada combinação
public abstract class ProdutoDecorator implements Produto {

    protected final Produto produto;

    /**
     * Cria o decorador envolvendo um produto.
     *
     * @param produto produto a ser decorado (básico ou já decorado)
     * @throws IllegalArgumentException se o produto for nulo
     */
    public ProdutoDecorator(Produto produto) {

        if (produto == null) {
            throw new IllegalArgumentException("O produto decorado não pode ser nulo.");
        }

        this.produto = produto;
    }

    /**
     * Retorna a descrição do produto decorado.
     *
     * @return a descrição do produto decorado
     */
    @Override
    public String getDescricao() {
        return produto.getDescricao();
    }

    /**
     * Retorna o preço do produto decorado.
     *
     * @return o preço do produto decorado
     */
    @Override
    public BigDecimal getPreco() {
        return produto.getPreco();
    }
}
