import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.sistema_relatorios.Models.RelatorioExcel;
import br.com.unesp.sistema_relatorios.Models.RelatorioPDF;
import br.com.unesp.sistema_relatorios.Models.SistemaRelatorio;
import br.com.unesp.sistema_relatorios.Models.SistemaRelatorioExcel;
import br.com.unesp.sistema_relatorios.Models.SistemaRelatorioPDF;

/**
 * Testes do padrão Factory Method: criação e geração de relatórios PDF e Excel.
 */
public class SistemaRelatorioTest {

    /**
     * Captura o que {@link SistemaRelatorio#gerarRelatorio()} imprime no console.
     *
     * @param sistema sistema que gerará o relatório
     * @return a saída do console, sem espaços nas pontas
     */
    // esse método serve para capturar a saida do gerar()
    // o gerar é void e não devolve nada então a gente faz uma gambiarra pra capturar a saida do System.out
    // e ao colocamos isso numa "caixa" temporária que é o ByteArrayOutputStream
    // show
    private String capturarSaida(SistemaRelatorio sistema) {
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(saida));

        try {
            sistema.gerarRelatorio();
        } finally {
            // o finally garante que o console volta ao normal mesmo se der exceção,
            // senão os outros testes ficariam imprimindo na caixinha
            System.setOut(original);
        }

        return saida.toString().trim();
    }

    /**
     * Verifica que o sistema PDF cria um {@link RelatorioPDF}.
     */
    @Test
    void sistemaPDFDeveCriarRelatorioPDF() {

        // given
        SistemaRelatorio sistema = new SistemaRelatorioPDF();

        // when / then
        assertInstanceOf(RelatorioPDF.class, sistema.criarRelatorio());

    }

    /**
     * Verifica que o sistema Excel cria um {@link RelatorioExcel}.
     */
    @Test
    void sistemaExcelDeveCriarRelatorioExcel() {

        // given
        SistemaRelatorio sistema = new SistemaRelatorioExcel();

        // when / then
        assertInstanceOf(RelatorioExcel.class, sistema.criarRelatorio());

    }

    /**
     * Verifica a saída da geração de um relatório PDF.
     */
    @Test
    void deveGerarRelatorioEmFormatoPDF() {

        // given
        SistemaRelatorio sistema = new SistemaRelatorioPDF();

        // when
        String saida = capturarSaida(sistema);

        // then
        assertEquals("Relatório gerado em formato PDF.", saida);

    }

    /**
     * Verifica a saída da geração de um relatório Excel.
     */
    @Test
    void deveGerarRelatorioEmFormatoExcel() {

        // given
        SistemaRelatorio sistema = new SistemaRelatorioExcel();

        // when
        String saida = capturarSaida(sistema);

        // then
        assertEquals("Relatório gerado em formato Excel.", saida);

    }
}
