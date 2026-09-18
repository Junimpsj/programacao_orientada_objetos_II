import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.unesp.logistica.model.EntregadorBike;

public class EntregadorBikeTest {

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

    @Test
    void deveLancarExcecaoParaTaxaBaseZero() {

        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorBike("Beatriz", "333.444.555-66", "N/A", 0.0, 15);
        });

    }

    @Test
    void deveLancarExcecaoParaDiasNegativos() {

        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorBike("Beatriz", "333.444.555-66", "N/A", 800.0, -1);
        });

    }

    @Test
    void deveLancarExcecaoParaDiasAcimaDoTotalDeDiasUteis() {

        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorBike("Beatriz", "333.444.555-66", "N/A", 800.0, 23);
        });
        
    }
}