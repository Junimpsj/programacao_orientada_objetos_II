package br.com.unesp.validacao_pedidos_chain.Chain;

import br.com.unesp.validacao_pedidos_chain.Models.Produto;

/**
 * ConcreteHandler: verifica se o endereço de entrega foi informado corretamente.
 */
public class ValidacaoEndereco extends ValidacaoPedido {

    /**
     * Interrompe o pedido se o endereço não foi informado; senão, repassa para a próxima etapa.
     *
     * @param produto produto do pedido a ser validado
     */
    @Override
    public void validar(Produto produto) {

        if (!produto.isEnderecoInformado()) {
            System.out.println("[ENDEREÇO] Pedido interrompido: endereço de entrega não informado.");
            return;
        }

        System.out.println("[ENDEREÇO] Endereço informado.");
        proximo.validar(produto);
    }
}
