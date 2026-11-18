package br.com.unesp.loja_virtual_decorator.Models;

import java.math.BigDecimal;

import br.com.unesp.loja_virtual_decorator.Interface.Produto;

/**
 * ConcreteComponent do Decorator: produto com seu nome e preço original, sem serviços adicionais.
 */
public class ProdutoBasico implements Produto {

    private final String nome;
    private final BigDecimal preco;

    /**
     * Cria um produto básico.
     *
     * @param nome nome do produto
     * @param preco preço original do produto, não pode ser negativo
     * @throws IllegalArgumentException se o nome for nulo/vazio ou o preço nulo/negativo
     */
    public ProdutoBasico(String nome, BigDecimal preco) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }

        if (preco == null || preco.signum() < 0) {
            throw new IllegalArgumentException("O preço do produto não pode ser nulo nem negativo.");
        }

        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return o nome do produto
     */
    @Override
    public String getDescricao() {
        return nome;
    }

    /**
     * Retorna o preço original do produto.
     *
     * @return o preço original em reais
     */
    @Override
    public BigDecimal getPreco() {
        return preco;
    }
}
