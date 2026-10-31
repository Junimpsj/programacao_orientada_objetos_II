package br.unesp.loja_virtual.model;

import br.unesp.loja_virtual.interfaces.ProdutoPrototype;

/**
 * Produto da loja, que pode ser clonado pelo padrão Prototype.
 */
public class Produto implements ProdutoPrototype {

    private String nome;
    private double preco;
    private String vendedor;
    private String categoria;

    /**
     * Cria um produto vazio.
     */
    public Produto() {
    }

    /**
     * Cria um produto com todos os dados.
     *
     * @param nome nome do produto
     * @param preco preço do produto
     * @param vendedor nome do vendedor
     * @param categoria categoria do produto
     */
    public Produto(String nome, double preco, String vendedor, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.vendedor = vendedor;
        this.categoria = categoria;
    }

    /**
     * Construtor de cópia usado pelo {@link #clone()}.
     *
     * @param outro produto a ser copiado
     */
    public Produto(Produto outro) {
        this.nome = outro.nome;
        this.preco = outro.preco;
        this.vendedor = outro.vendedor;
        this.categoria = outro.categoria;
    }

    /**
     * Cria uma cópia independente deste produto.
     *
     * @return um novo {@link Produto} com os mesmos dados
     */
    @Override
    public Produto clone() {
        return new Produto(this);
    }

    /**
     * Retorna o nome do produto.
     *
     * @return o nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome novo nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return o preço do produto
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco novo preço do produto
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna o vendedor do produto.
     *
     * @return o nome do vendedor
     */
    public String getVendedor() {
        return vendedor;
    }

    /**
     * Define o vendedor do produto.
     *
     * @param vendedor novo vendedor do produto
     */
    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * Retorna a categoria do produto.
     *
     * @return a categoria do produto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria nova categoria do produto
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}