package br.com.unesp.sistema_relatorios.Models;

import br.com.unesp.sistema_relatorios.Interface.Relatorio;

/**
 * Creator concreto que produz relatórios em PDF.
 */
public class SistemaRelatorioPDF extends SistemaRelatorio {

    /**
     * {@inheritDoc}
     *
     * @return um {@link RelatorioPDF}
     */
    @Override
    public Relatorio criarRelatorio() {
        return new RelatorioPDF();
    }
}
