package br.com.unesp.compras_mercado.Model;

/**
 * Produto do mercado, com nome, preço e quantidade em estoque.
 */
public class Produto {

    /**
     * Nome do produto.
     */
    private String nome;
    /**
     * Preço unitário do produto.
     */
    private double preco;
    /**
     * Quantidade em estoque.
     */
    private int qtdEstoque;

    /**
     * Cria um produto.
     *
     * @param nome nome do produto
     * @param preco preço unitário, deve ser maior que zero
     * @param qtdEstoque quantidade em estoque, não pode ser negativa
     * @throws IllegalArgumentException se o preço for menor ou igual a zero ou o estoque for negativo
     */
    public Produto(String nome, double preco, int qtdEstoque) {

        if(preco <= 0) {throw new IllegalArgumentException("O preço do produto deve ser maior que zero!");}

        if(qtdEstoque < 0) {throw new IllegalArgumentException("A quantidade do produto não pode ser negativa!");}

        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;

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
     * Retorna o preço do produto.
     *
     * @return o preço unitário
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco novo preço, deve ser maior que zero
     * @throws IllegalArgumentException se o preço for menor ou igual a zero
     */
    public void setPreco(double preco) {
        if(preco <= 0) {throw new IllegalArgumentException("O preço do produto deve ser maior que zero!");}
        this.preco = preco;
    }

    /**
     * Retorna a quantidade em estoque.
     *
     * @return a quantidade em estoque
     */
    public int getQtdEstoque() {
        return qtdEstoque;
    }

    /**
     * Dá baixa no estoque.
     *
     * @param quantidade quantidade a retirar do estoque
     * @throws IllegalArgumentException se a quantidade for maior que o estoque disponível
     */
    // método para baixar o estoque quando algum pedido for feito
    // como manipula diretamente a qtdEstoque é implementada aqui para ser chamada depois
    public void baixarEstoque(int quantidade) {

        if(quantidade > qtdEstoque) {throw new IllegalArgumentException("Estoque insuficiente!");}

        this.qtdEstoque -= quantidade;

    }
    
}