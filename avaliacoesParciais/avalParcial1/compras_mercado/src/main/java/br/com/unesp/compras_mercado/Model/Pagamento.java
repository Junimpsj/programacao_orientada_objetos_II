package br.com.unesp.compras_mercado.Model;

/**
 * Forma de pagamento de um {@link Pedido}.
 */
public abstract class Pagamento {
    
    /**
     * Retorna o texto do método de pagamento.
     *
     * @return a descrição do método de pagamento
     */
    // método abstrato que deve retornar uma String com o texto do método de pagamento escolhido
    protected abstract String metodoPagamento();

}