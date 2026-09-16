import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.unesp.logistica.model.EntregadorCarro;

public class EntregadorCarroTest {

    @Test
    void deveCalcularBonusSemAdicionalDeFrotaQuandoMetaPonderadaNaoAtingida() {
        // given
        EntregadorCarro carro = new EntregadorCarro("Marcos", "222.333.444-55", "XYZ-5678", 1000.0, 0.5);
        double bonusEsperado = 700.0; // 1000 * (0.5 * 1.4), sem bônus fixo

        // when
        double bonusCalculado = carro.calcularBonus();

        // then
        assertEquals(bonusEsperado, bonusCalculado, 0.0001);
    }

    @Test
    void deveConcederBonusDeFrotaQuandoMetaPonderadaAtingeExatamente100PorCento() {
        // given
        double percentual = 1.0 / 1.4; // ponderado dá exatamente 1.0
        EntregadorCarro carro = new EntregadorCarro("Marcos", "222.333.444-55", "XYZ-5678", 1000.0, percentual);
        double bonusEsperado = 1600.0; // 1000 * 1.0 + 600 fixo

        // when
        double bonusCalculado = carro.calcularBonus();

        // then
        assertEquals(bonusEsperado, bonusCalculado, 0.0001);
    }

    @Test
    void naoDeveConcederBonusDeFrotaLogoAbaixoDaMetaPonderada() {
        // given
        double percentual = (1.0 / 1.4) - 0.01; // fica logo abaixo de 1.0 ponderado
        EntregadorCarro carro = new EntregadorCarro("Marcos", "222.333.444-55", "XYZ-5678", 1000.0, percentual);

        // when
        double bonusCalculado = carro.calcularBonus();

        // then
        assertTrue(bonusCalculado < 1600.0); // não deve incluir os 600 fixos
    }

    @Test
    void deveLancarExcecaoParaTaxaBaseNegativa() {
        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorCarro("Marcos", "222.333.444-55", "XYZ-5678", -1000.0, 0.8);
        });
    }

    @Test
    void deveLancarExcecaoParaPercentualDaMetaNegativo() {
        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            new EntregadorCarro("Marcos", "222.333.444-55", "XYZ-5678", 1000.0, -0.5);
        });
    }
}