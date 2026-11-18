import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.validador_codigos_interpreter.Aplication.App;
import br.com.unesp.validador_codigos_interpreter.Interpreter.RegularExpression;

/**
 * Testes do padrão Interpreter: a árvore sintática reconhece apenas os códigos
 * da gramática {@code A(B|C)*J}.
 */
public class ValidadorCodigosInterpreterTest {

    private static final RegularExpression EXPRESSAO = App.criarExpressao();

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
     * Verifica códigos que começam com A, têm zero ou mais B ou C e terminam com J.
     */
    @Test
    void deveAceitarCodigosDaGramatica() {
        for (String codigo : new String[] {"AJ", "ABJ", "ACJ", "ABCBCJ", "ACCCBJ"}) {
            assertTrue(App.validar(EXPRESSAO, codigo), codigo);
        }
    }

    /**
     * Verifica códigos sem o J no final, sem o A no início, com J fora do fim ou com símbolo inválido.
     */
    @Test
    void deveRejeitarCodigosForaDaGramatica() {
        for (String codigo : new String[] {"A", "J", "ABC", "AJB", "AJJ", "BJ", "ADJ", "ABDJ", "AAJ", ""}) {
            assertFalse(App.validar(EXPRESSAO, codigo), codigo);
        }
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
                "AJ" -> Válido
                "ABJ" -> Válido
                "ACJ" -> Válido
                "ABCBCJ" -> Válido
                "A" -> Inválido
                "J" -> Inválido
                "ABC" -> Inválido
                "AJB" -> Inválido
                "AJJ" -> Inválido
                "BJ" -> Inválido
                "ADJ" -> Inválido
                "ABDJ" -> Inválido
                "" -> Inválido""", saida);

    }
}
