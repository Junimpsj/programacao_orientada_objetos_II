package br.com.unesp.compras_mercado.Model;

public class ItemPedido {

    private Produto produto; // produto é do tipo Produto para que possamos manipular diretamente atributos da classe como qtdEstoque etc...
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {

        if(quantidade <= 0) {throw new IllegalArgumentException("A quantidade de itens deve ser maior que zero!");}

        this.produto = produto;
        this.quantidade = quantidade;

    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // método que retorna o subTotal da compra do ItemPedido, pega o preco diretamente do atributo do produto instanciado
    public double calcularSubtotal() {

        return produto.getPreco() * quantidade;

    }
    
}