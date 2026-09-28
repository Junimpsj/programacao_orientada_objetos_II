import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.unesp.polinomio.model.Polinomio;
import br.com.unesp.polinomio.model.Termo;

class PolinomioTest {

    private Polinomio p1;

    @BeforeEach
    void setUp() {
        // P1(x) = 3x^2 + 5x + 2
        p1 = new Polinomio(new Termo(new BigDecimal("3"), 2));
        p1.inserir(new Termo(new BigDecimal("5"), 1));
        p1.inserir(new Termo(new BigDecimal("2"), 0));
    }

    @Test
    void deveCriarPolinomioComTermoInicial() {

        // given - when
        Polinomio polinomio = new Polinomio(new Termo(new BigDecimal("3"), 2));

        // then
        assertEquals(1, polinomio.getTermos().size());
    }

    @Test
    void deveInserirNovoTermoQuandoExpoenteNaoExiste() {

        // given - when
        p1.inserir(new Termo(new BigDecimal("1"), 3));

        // then
        assertEquals(4, p1.getTermos().size());
    }

    @Test
    void deveUnificarTermosComMesmoExpoenteAoInserir() {

        // given - when
        // já existe 3x^2, inserindo 4x^2 deve virar 7x^2 (sem criar termo novo)
        p1.inserir(new Termo(new BigDecimal("4"), 2));

        // then
        assertEquals(3, p1.getTermos().size());
        assertEquals(0, new BigDecimal("7").compareTo(p1.getTermos().get(0).getCoeficiente()));
    }

    @Test
    void deveCalcularValorDoPolinomio() {

        // given - when
        // 3x^2 + 5x + 2 em x=2 -> 12 + 10 + 2 = 24
        BigDecimal resultado = p1.calcula(new BigDecimal("2"));

        // then
        assertEquals(0, new BigDecimal("24").compareTo(resultado));
    }

    @Test
    void deveFundirDoisPolinomios() {

        // given
        // P2(x) = 4x^2 + 2x + 7
        Polinomio p2 = new Polinomio(new Termo(new BigDecimal("4"), 2));
        p2.inserir(new Termo(new BigDecimal("2"), 1));
        p2.inserir(new Termo(new BigDecimal("7"), 0));

        // when
        p1.fusao(p2);

        // then
        // resultado esperado: 7x^2 + 7x + 9 -> em x=1: 7 + 7 + 9 = 23
        assertEquals(3, p1.getTermos().size());
        assertEquals(0, new BigDecimal("23").compareTo(p1.calcula(BigDecimal.ONE)));
    }

    @Test
    void deveUnificarTermosComMesmoExpoenteDuranteFusao() {

        // given
        Polinomio p2 = new Polinomio(new Termo(new BigDecimal("4"), 2));

        // when
        p1.fusao(p2);

        // then
        // só o expoente 2 já existia em ambos -> continua com 3 termos, coeficiente unificado 7
        assertEquals(3, p1.getTermos().size());
        assertEquals(0, new BigDecimal("7").compareTo(p1.getTermos().get(0).getCoeficiente()));
    }
}
