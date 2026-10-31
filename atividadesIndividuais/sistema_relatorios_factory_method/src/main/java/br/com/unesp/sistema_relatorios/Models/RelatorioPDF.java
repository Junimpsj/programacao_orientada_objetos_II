package br.com.unesp.sistema_relatorios.Models;

import br.com.unesp.sistema_relatorios.Interface.Relatorio;

/**
 * Produto concreto: relatório em formato PDF.
 */
public class RelatorioPDF implements Relatorio {

    /**
     * {@inheritDoc}
     */
    @Override
    public void gerar() {
        System.out.println("Relatório gerado em formato PDF.");
    }
}
