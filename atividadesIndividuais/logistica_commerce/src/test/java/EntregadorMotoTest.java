import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.unesp.logistica.model.EntregadorMoto;

public class EntregadorMotoTest {

    @Test
    void deveCalcularBonusSemAdicionalQuandoMetaNaoAtingida() {
        // given
        EntregadorMoto moto = new EntregadorMoto("João", "111.222.333-44", "ABC-1234", 1000.0, 0.5);
        double bonusEsperado = 500.0; // 1000 * 0.5, sem bônus fixo

        // when
        double bonusCalculado = moto.calcularBonus();

        // then
        assertEquals(bonusEsperado, bonusCalculado, 0.0001);
    }

    @Test
    void deveConcederBonusFixoQuandoMetaForExatamente100PorCento() {
        // given
        EntregadorMoto moto = new EntregadorMoto("João", "111.222.333-44", "ABC-1234", 1000.0, 1.0);
        double bonusEsperado = 1300.0; // 1000 * 1.0 + 300 fixo

        // when
        double bonusCalculado = moto.calcularBonus();

        // then
        assertEquals(bonusEsperado, bonusCalculado, 0.0001);
    }

    @Test
    void naoDeveConcederBonusFixoLogoAbaixoDaMeta() {
        // given
        EntregadorMoto moto = new EntregadorMoto("João", "111.222.333-44", "ABC-1234", 1000.0, 0.99);
        double bonusEsperado = 990.0; // sem os 300 fixos

        // when
        double bonusCalculado = moto.calcularBonus();

        // then
        assertEquals(bonusEsperado, bonusCalculado, 0.0001);
    }

    @Test
    void deveLancarExcecaoParaTaxaBaseZero() {
        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorMoto("João", "111.222.333-44", "ABC-1234", 0.0, 1.0);
        });
    }

    @Test
    void deveLancarExcecaoParaTaxaBaseNegativa() {
        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorMoto("João", "111.222.333-44", "ABC-1234", -500.0, 1.0);
        });
    }

    @Test
    void deveLancarExcecaoParaPercentualDaMetaNegativo() {
        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorMoto("João", "111.222.333-44", "ABC-1234", 1000.0, -0.1);
        });
    }
}