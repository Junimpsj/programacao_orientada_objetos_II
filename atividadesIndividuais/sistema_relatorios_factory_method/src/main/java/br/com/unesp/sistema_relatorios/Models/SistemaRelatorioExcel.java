package br.com.unesp.sistema_relatorios.Models;

import br.com.unesp.sistema_relatorios.Interface.Relatorio;

/**
 * Creator concreto que produz relatórios em Excel.
 */
public class SistemaRelatorioExcel extends SistemaRelatorio {

    /**
     * {@inheritDoc}
     *
     * @return um {@link RelatorioExcel}
     */
    @Override
    public Relatorio criarRelatorio() {
        return new RelatorioExcel();
    }
}
