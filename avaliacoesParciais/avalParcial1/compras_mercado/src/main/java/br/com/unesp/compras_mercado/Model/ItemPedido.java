package br.com.unesp.compras_mercado.Model;

/**
 * Item de um {@link Pedido}: um {@link Produto} e a quantidade comprada.
 */
public class ItemPedido {

    /**
     * Produto comprado.
     */
    private Produto produto; // produto é do tipo Produto para que possamos manipular diretamente atributos da classe como qtdEstoque etc...
    /**
     * Quantidade comprada.
     */
    private int quantidade;

    /**
     * Cria um item de pedido.
     *
     * @param produto produto comprado
     * @param quantidade quantidade comprada, deve ser maior que zero
     * @throws IllegalArgumentException se a quantidade for menor ou igual a zero
     */
    public ItemPedido(Produto produto, int quantidade) {

        if(quantidade <= 0) {throw new IllegalArgumentException("A quantidade de itens deve ser maior que zero!");}

        this.produto = produto;
        this.quantidade = quantidade;

    }

    /**
     * Retorna o produto do item.
     *
     * @return o produto do item
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Retorna a quantidade do item.
     *
     * @return a quantidade comprada
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Calcula o subtotal do item (preço do produto vezes a quantidade).
     *
     * @return o subtotal do item
     */
    // método que retorna o subTotal da compra do ItemPedido, pega o preco diretamente do atributo do produto instanciado
    public double calcularSubtotal() {

        return produto.getPreco() * quantidade;

    }
    
}