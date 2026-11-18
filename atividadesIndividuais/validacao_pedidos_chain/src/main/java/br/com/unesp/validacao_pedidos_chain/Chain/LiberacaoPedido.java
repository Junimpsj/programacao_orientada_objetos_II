package br.com.unesp.validacao_pedidos_chain.Chain;

import br.com.unesp.validacao_pedidos_chain.Models.Produto;

/**
 * ConcreteHandler: última etapa da cadeia. Só é alcançada quando todas as
 * verificações anteriores passaram, então apenas confirma a liberação do pedido.
 */
public class LiberacaoPedido extends ValidacaoPedido {

    /**
     * Confirma que o pedido passou por todas as verificações e foi liberado.
     *
     * @param produto produto do pedido liberado
     */
    // fim da cadeia, não repassa para ninguém (então não depende do proximo e não tem risco de NullPointerException)
    @Override
    public void validar(Produto produto) {
        System.out.println("[LIBERAÇÃO] Pedido de " + produto.getNome() + " liberado para processamento.");
    }
}
