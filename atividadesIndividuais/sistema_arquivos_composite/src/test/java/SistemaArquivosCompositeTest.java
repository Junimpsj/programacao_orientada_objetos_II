import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.sistema_arquivos_composite.Aplication.App;
import br.com.unesp.sistema_arquivos_composite.Composite.Pasta;
import br.com.unesp.sistema_arquivos_composite.Models.Arquivo;

/**
 * Testes do padrão Composite: tamanho recursivo, adição/remoção e exibição da hierarquia.
 */
public class SistemaArquivosCompositeTest {

    /**
     * Captura o que for impresso no console durante a ação.
     *
     * @param acao trecho de código a ser executado
     * @return a saída do console, sem espaços nas pontas
     */
    private String capturarSaida(Runnable acao) {
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(saida));

        try {
            acao.run();
        } finally {
            System.setOut(original);
        }

        return saida.toString().trim().replace("\r\n", "\n");
    }

    /**
     * Verifica que o tamanho da pasta soma arquivos de todas as subpastas.
     */
    @Test
    void deveCalcularTamanhoComSubpastas() {

        // given
        Pasta raiz = new Pasta("raiz");
        Pasta sub = new Pasta("sub");
        sub.adicionar(new Arquivo("b.txt", 20));
        raiz.adicionar(new Arquivo("a.txt", 10));
        raiz.adicionar(sub);

        // when
        long tamanho = raiz.getTamanho();

        // then
        assertEquals(30, tamanho);

    }

    /**
     * Verifica que a pasta vazia tem tamanho zero.
     */
    @Test
    void pastaVaziaDeveTerTamanhoZero() {
        assertEquals(0, new Pasta("vazia").getTamanho());
    }

    /**
     * Verifica que a remoção de um elemento atualiza o tamanho.
     */
    @Test
    void deveRemoverElemento() {

        // given
        Pasta raiz = new Pasta("raiz");
        Arquivo arquivo = new Arquivo("a.txt", 10);
        raiz.adicionar(arquivo);
        raiz.adicionar(new Arquivo("b.txt", 5));

        // when
        boolean removido = raiz.remover(arquivo);

        // then
        assertTrue(removido);
        assertEquals(5, raiz.getTamanho());
        assertFalse(raiz.remover(arquivo));

    }

    /**
     * Verifica as validações de entrada.
     */
    @Test
    void deveRejeitarEntradasInvalidas() {

        Pasta pasta = new Pasta("pasta");

        assertThrows(IllegalArgumentException.class, () -> pasta.adicionar(pasta));
        assertThrows(IllegalArgumentException.class, () -> pasta.adicionar(null));
        assertThrows(IllegalArgumentException.class, () -> new Arquivo("a.txt", -1));

    }

    /**
     * Verifica a saída completa de {@link App#main(String[])}.
     */
    @Test
    void deveExecutarDemonstracaoCompleta() {

        // when
        String saida = capturarSaida(() -> App.main(new String[0]));

        // then
        assertEquals("""
                === ESTRUTURA ===
                [Pasta] documentos (2574 KB)
                  [Arquivo] curriculo.pdf (120 KB)
                  [Pasta] faculdade (406 KB)
                    [Pasta] poo2 (156 KB)
                      [Arquivo] atividade_composite.pdf (80 KB)
                      [Arquivo] atividade_decorator.pdf (76 KB)
                    [Arquivo] calendario.png (250 KB)
                  [Pasta] fotos (2048 KB)
                    [Arquivo] praia.jpg (2048 KB)
                Tamanho total de documentos: 2574 KB
                === REMOVENDO A PASTA fotos ===
                [Pasta] documentos (526 KB)
                  [Arquivo] curriculo.pdf (120 KB)
                  [Pasta] faculdade (406 KB)
                    [Pasta] poo2 (156 KB)
                      [Arquivo] atividade_composite.pdf (80 KB)
                      [Arquivo] atividade_decorator.pdf (76 KB)
                    [Arquivo] calendario.png (250 KB)
                Tamanho total de documentos: 526 KB""", saida);

    }
}
