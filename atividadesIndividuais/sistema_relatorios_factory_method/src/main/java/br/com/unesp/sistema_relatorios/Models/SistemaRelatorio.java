package br.com.unesp.sistema_relatorios.Models;

import br.com.unesp.sistema_relatorios.Interface.Relatorio;

/**
 * Creator do Factory Method: define o fluxo de geração e deixa para as subclasses
 * a escolha do {@link Relatorio} concreto.
 */
public abstract class SistemaRelatorio {

    /**
     * Factory method que cria o relatório concreto.
     *
     * @return o relatório criado pela subclasse
     */
    // factory method, fica sob responsabilidade da subclasse (classe filha) qual relatório concreto criar
    public abstract Relatorio criarRelatorio();

    /**
     * Cria o relatório pelo factory method e o gera.
     */
    public void gerarRelatorio() {
        Relatorio relatorio = criarRelatorio();
        relatorio.gerar();
    }
}
