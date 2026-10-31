package br.com.unesp.sistema_relatorios.Interface;

/**
 * Produto do Factory Method: um relatório que sabe se gerar no seu formato.
 */
public interface Relatorio {
    /**
     * Gera o relatório.
     */
    void gerar();
}
