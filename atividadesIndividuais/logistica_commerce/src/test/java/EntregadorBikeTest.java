import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.unesp.logistica.model.EntregadorBike;

/**
 * Testes do cálculo de bônus e das validações do {@link EntregadorBike}.
 */
public class EntregadorBikeTest {

    /**
     * Verifica que o bônus é zero abaixo do mínimo de dias de meta batida.
     */
    @Test
    void deveZerarBonusAbaixoDaRegraDeCorte() {

        // given
        EntregadorBike bike = new EntregadorBike("Beatriz", "333.444.555-66", "N/A", 800.0, 10);
        double bonusEsperado = 0.0;

        // when
        double bonusCalculado = bike.calcularBonus();

        // then
        assertEquals(bonusEsperado, bonusCalculado, 0.0001);

    }

    /**
     * Verifica que o bônus é concedido exatamente no mínimo de dias de meta batida.
     */
    @Test
    void deveConcederBonusExatamenteNoLimiteDeCorte() {

        // given
        EntregadorBike bike = new EntregadorBike("Beatriz", "333.444.555-66", "N/A", 800.0, 11);
        double bonusEsperado = 800.0 * (11.0 / 22.0); // proporcional, sem zerar

        // when
        double bonusCalculado = bike.calcularBonus();

        // then
        assertEquals(bonusEsperado, bonusCalculado, 0.0001);

    }

    /**
     * Verifica que o bônus equivale à taxa base quando todos os dias úteis têm meta batida.
     */
    @Test
    void deveCalcularBonusMaximoComTodosOsDiasTrabalhados() {

        // given
        EntregadorBike bike = new EntregadorBike("Beatriz", "333.444.555-66", "N/A", 800.0, 22);
        double bonusEsperado = 800.0; // proporção 22/22 = 1.0

        // when
        double bonusCalculado = bike.calcularBonus();

        // then
        assertEquals(bonusEsperado, bonusCalculado, 0.0001);

    }

    /**
     * Verifica que taxa base zero lança {@link IllegalArgumentException}.
     */
    @Test
    void deveLancarExcecaoParaTaxaBaseZero() {

        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorBike("Beatriz", "333.444.555-66", "N/A", 0.0, 15);
        });

    }

    /**
     * Verifica que dias de meta batida negativos lançam {@link IllegalArgumentException}.
     */
    @Test
    void deveLancarExcecaoParaDiasNegativos() {

        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorBike("Beatriz", "333.444.555-66", "N/A", 800.0, -1);
        });

    }

    /**
     * Verifica que mais dias que o total de dias úteis lança {@link IllegalArgumentException}.
     */
    @Test
    void deveLancarExcecaoParaDiasAcimaDoTotalDeDiasUteis() {

        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorBike("Beatriz", "333.444.555-66", "N/A", 800.0, 23);
        });
        
    }
}