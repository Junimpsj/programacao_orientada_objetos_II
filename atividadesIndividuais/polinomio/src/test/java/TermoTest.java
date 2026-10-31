import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.unesp.polinomio.model.Termo;

/**
 * Testes da classe {@link Termo}.
 */
class TermoTest {

    /**
     * Verifica que o termo guarda coeficiente e expoente informados.
     */
    @Test
    void deveCriarTermoComCoeficienteEExpoente() {

        // given - when
        Termo termo = new Termo(new BigDecimal("3"), 2);

        // then
        assertEquals(0, new BigDecimal("3").compareTo(termo.getCoeficiente()));
        assertEquals(2, termo.getExpoente());
    }

    /**
     * Verifica que coeficiente nulo lança {@link IllegalArgumentException}.
     */
    @Test
    void deveLancarExcecaoParaCoeficienteNulo() {

        // given - when - then
        assertThrows(IllegalArgumentException.class, () -> {
            new Termo(null, 2);
        });
    }

    /**
     * Verifica que expoente negativo lança {@link IllegalArgumentException}.
     */
    @Test
    void deveLancarExcecaoParaExpoenteNegativo() {

        // given - when - then
        assertThrows(IllegalArgumentException.class, () -> {
            new Termo(new BigDecimal("3"), -1);
        });
    }

    /**
     * Verifica o cálculo do valor do termo para um x.
     */
    @Test
    void deveCalcularValorDoTermo() {

        // given
        Termo termo = new Termo(new BigDecimal("3"), 2);

        // when
        BigDecimal resultado = termo.calcula(new BigDecimal("2"));

        // then
        assertEquals(0, new BigDecimal("12").compareTo(resultado));
    }

    /**
     * Verifica que {@link Termo#insere(Termo)} substitui os valores do termo corrente.
     */
    @Test
    void deveInserirSubstituindoValoresDoTermoCorrente() {

        // given
        Termo termo = new Termo(new BigDecimal("3"), 2);
        Termo novoTermo = new Termo(new BigDecimal("7"), 4);

        // when
        termo.insere(novoTermo);

        // then
        assertEquals(0, new BigDecimal("7").compareTo(termo.getCoeficiente()));
        assertEquals(4, termo.getExpoente());
    }
}
