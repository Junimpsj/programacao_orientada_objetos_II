package br.com.unesp.validacao_pedidos_chain.Chain;

import br.com.unesp.validacao_pedidos_chain.Models.Produto;

/**
 * ConcreteHandler: verifica se o pagamento do pedido foi aprovado.
 */
public class ValidacaoPagamento extends ValidacaoPedido {

    /**
     * Interrompe o pedido se o pagamento não foi aprovado; senão, repassa para a próxima etapa.
     *
     * @param produto produto do pedido a ser validado
     */
    @Override
    public void validar(Produto produto) {

        if (!produto.isPagamentoAprovado()) {
            System.out.println("[PAGAMENTO] Pedido interrompido: pagamento não aprovado.");
            return;
        }

        System.out.println("[PAGAMENTO] Pagamento aprovado.");
        proximo.validar(produto);
    }
}
