package br.com.unesp.compras_mercado.Model;

public class Produto {

    private String nome;
    private double preco;
    private int qtdEstoque;

    public Produto(String nome, double preco, int qtdEstoque) {

        if(preco <= 0) {throw new IllegalArgumentException("O preço do produto deve ser maior que zero!");}

        if(qtdEstoque < 0) {throw new IllegalArgumentException("A quantidade do produto não pode ser negativa!");}

        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;

    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if(preco <= 0) {throw new IllegalArgumentException("O preço do produto deve ser maior que zero!");}
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    // método para baixar o estoque quando algum pedido for feito
    // como manipula diretamente a qtdEstoque é implementada aqui para ser chamada depois
    public void baixarEstoque(int quantidade) {

        if(quantidade > qtdEstoque) {throw new IllegalArgumentException("Estoque insuficiente!");}

        this.qtdEstoque -= quantidade;

    }
    
}