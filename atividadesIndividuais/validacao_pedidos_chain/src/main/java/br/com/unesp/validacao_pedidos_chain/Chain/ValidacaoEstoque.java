package br.com.unesp.validacao_pedidos_chain.Chain;

import br.com.unesp.validacao_pedidos_chain.Models.Produto;

/**
 * ConcreteHandler: primeira etapa da cadeia, verifica se o produto do pedido
 * está disponível em estoque.
 */
public class ValidacaoEstoque extends ValidacaoPedido {

    /**
     * Interrompe o pedido se o produto estiver fora de estoque; senão, repassa para a próxima etapa.
     *
     * @param produto produto do pedido a ser validado
     */
    // diferente do exemplo do suporte: aqui quem encerra a cadeia é a etapa que acha problema
    @Override
    public void validar(Produto produto) {

        if (!produto.isEmEstoque()) {
            System.out.println("[ESTOQUE] Pedido interrompido: produto fora de estoque.");
            return;
        }

        System.out.println("[ESTOQUE] Produto disponível.");
        proximo.validar(produto);
    }
}
