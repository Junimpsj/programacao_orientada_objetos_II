package br.com.unesp.validacao_pedidos_chain.Chain;

import br.com.unesp.validacao_pedidos_chain.Models.Produto;

/**
 * Handler do Chain of Responsibility: define a interface comum das etapas de
 * validação e guarda a referência para a próxima etapa da cadeia.
 */
// quem envia o pedido só conhece o primeiro elo, não sabe quantas etapas existem nem a ordem
public abstract class ValidacaoPedido {

    // protected porque as subclasses precisam repassar o pedido
    protected ValidacaoPedido proximo;

    /**
     * Define a próxima etapa da cadeia.
     *
     * @param proximo etapa que recebe o pedido caso esta não encontre problemas
     */
    public void setProximo(ValidacaoPedido proximo) {
        this.proximo = proximo;
    }

    /**
     * Realiza a verificação desta etapa. Se encontrar problema, informa o motivo
     * e interrompe o processamento; caso contrário, encaminha o pedido para a próxima etapa.
     *
     * @param produto produto do pedido a ser validado
     */
    public abstract void validar(Produto produto);
}
