package br.com.unesp.compras_mercado.Model;

import java.util.List;
import java.util.ArrayList;

public class Pedido {

    private List<ItemPedido> itens = new ArrayList<>();
    private Pagamento formaPagamento;

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Pagamento getFormaPagamento() {
        return formaPagamento;
    }

    // adicionando itens no pedido (ItemPedido -> lista de ItemPedido "itens" na classe Pedido)
    public void adicionarItem(ItemPedido item) {

        itens.add(item);

    }

    // método para selecionar a forma de pagamento
    public void definirFormaPagamento(Pagamento formaPagamento) {

        this.formaPagamento = formaPagamento;

    }

    // for-each para calcular o total do pedido calculando o subTotal de cada ItemPedido e somando
    public double calcularTotal() {

        double total = 0;

        for(ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }

        return total;

    }

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