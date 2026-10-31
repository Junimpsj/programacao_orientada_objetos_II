package br.com.unesp.sistema_relatorios.Aplication;

import br.com.unesp.sistema_relatorios.Models.SistemaRelatorio;
import br.com.unesp.sistema_relatorios.Models.SistemaRelatorioExcel;
import br.com.unesp.sistema_relatorios.Models.SistemaRelatorioPDF;

/**
 * Ponto de entrada que demonstra o padrão Factory Method gerando um relatório em PDF e outro em Excel.
 */
public class App {

    /**
     * Gera um relatório em cada formato.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        SistemaRelatorio sistemaPDF = new SistemaRelatorioPDF();
        sistemaPDF.gerarRelatorio();

        SistemaRelatorio sistemaExcel = new SistemaRelatorioExcel();
        sistemaExcel.gerarRelatorio();
    }
}
