package br.com.unesp.loja_virtual_decorator.Interface;

import java.math.BigDecimal;

/**
 * Component do Decorator: contrato comum ao produto básico e aos produtos decorados com serviços.
 */
public interface Produto {
    /**
     * Retorna a descrição do produto, incluindo os serviços adicionais contratados.
     *
     * @return a descrição do produto
     */
    String getDescricao();
    /**
     * Retorna o preço do produto, incluindo os serviços adicionais contratados.
     *
     * @return o preço em reais
     */
    BigDecimal getPreco();
}
