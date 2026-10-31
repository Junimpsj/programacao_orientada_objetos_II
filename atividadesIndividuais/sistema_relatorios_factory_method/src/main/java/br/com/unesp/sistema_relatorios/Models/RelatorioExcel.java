package br.com.unesp.sistema_relatorios.Models;

import br.com.unesp.sistema_relatorios.Interface.Relatorio;

/**
 * Produto concreto: relatório em formato Excel.
 */
public class RelatorioExcel implements Relatorio {

    /**
     * {@inheritDoc}
     */
    @Override
    public void gerar() {
        System.out.println("Relatório gerado em formato Excel.");
    }
}
