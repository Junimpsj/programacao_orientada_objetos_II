package br.com.unesp.compras_mercado.Model;

import java.util.List;
import java.util.ArrayList;

/**
 * Pedido de compra: itens, forma de pagamento, total e baixa de estoque ao finalizar.
 */
public class Pedido {

    /**
     * Itens do pedido.
     */
    private List<ItemPedido> itens = new ArrayList<>();
    /**
     * Forma de pagamento escolhida.
     */
    private Pagamento formaPagamento;

    /**
     * Retorna os itens do pedido.
     *
     * @return a lista de itens
     */
    public List<ItemPedido> getItens() {
        return itens;
    }

    /**
     * Retorna a forma de pagamento.
     *
     * @return a forma de pagamento, ou {@code null} se ainda não foi definida
     */
    public Pagamento getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * Adiciona um item ao pedido.
     *
     * @param item item a ser adicionado
     */
    // adicionando itens no pedido (ItemPedido -> lista de ItemPedido "itens" na classe Pedido)
    public void adicionarItem(ItemPedido item) {

        itens.add(item);

    }

    /**
     * Define a forma de pagamento do pedido.
     *
     * @param formaPagamento forma de pagamento escolhida
     */
    // método para selecionar a forma de pagamento
    public void definirFormaPagamento(Pagamento formaPagamento) {

        this.formaPagamento = formaPagamento;

    }

    /**
     * Calcula o total do pedido somando o subtotal de cada item.
     *
     * @return o total do pedido
     */
    // for-each para calcular o total do pedido calculando o subTotal de cada ItemPedido e somando
    public double calcularTotal() {

        double total = 0;

        for(ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }

        return total;

    }

    /**
     * Finaliza o pedido, dando baixa no estoque de cada produto comprado.
     *
     * @throws IllegalStateException se a forma de pagamento não foi definida
     */
    // finalizando o pedido, quer dizer que damos baixa no estoque de cada produto que foi "comprado"
    // e tambem selecioamos a forma de pagamento desejada
    public void finalizar() {

        if(formaPagamento == null) {
            throw new IllegalStateException("Forma de pagamento não foi definida!");
        }

        for(ItemPedido item : itens) {
            item.getProduto().baixarEstoque(item.getQuantidade());
        }

    }
    
}