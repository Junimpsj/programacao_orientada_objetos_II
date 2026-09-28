import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.unesp.model.ContaPoupanca;

class ContaPoupancaTest {

    private ContaPoupanca conta;

    @BeforeEach
    void setUp() {
        conta = new ContaPoupanca(
                1,
                "João Potter da Silva",
                new BigDecimal("1000.00"),
                12345,
                123,
                new BigDecimal("0.005"));
    }

    @Test
    void deveRealizarDepositoComBonusDeUmPorCento() {
        // Given
        BigDecimal valorDeposito = new BigDecimal("100.00");
        BigDecimal valorEsperado = new BigDecimal("1101.00");

        // When
        conta.depositar(valorDeposito);
        BigDecimal valorObtido = conta.getSaldo();

        // Then
        assertEquals(
                0,
                valorEsperado.compareTo(valorObtido));
    }

    @Test
    void deveAplicarBonusReduzidoAoUltrapassarLimite() {
        // Given
        conta = new ContaPoupanca(
                1,
                "João",
                new BigDecimal("4900.00"),
                12345,
                123,
                new BigDecimal("0.005"));

        BigDecimal valorDeposito = new BigDecimal("200.00");
        BigDecimal valorEsperado = new BigDecimal("5101.00");

        // When
        conta.depositar(valorDeposito);
        BigDecimal valorObtido = conta.getSaldo();

        // Then
        assertEquals(
                0,
                valorEsperado.compareTo(valorObtido));
    }

    @Test
    void deveAplicarBonusNormalQuandoSaldoProjetadoForIgualAoLimite() {
        // Given
        conta = new ContaPoupanca(
                1,
                "João",
                new BigDecimal("4900.00"),
                12345,
                123,
                new BigDecimal("0.005"));

        BigDecimal valorDeposito = new BigDecimal("100.00");
        BigDecimal valorEsperado = new BigDecimal("5001.00");

        // When
        conta.depositar(valorDeposito);
        BigDecimal valorObtido = conta.getSaldo();

        // Then
        assertEquals(
                0,
                valorEsperado.compareTo(valorObtido));
    }

    @Test
    void naoDevePermitirDepositoNulo() {
        // Given
        BigDecimal valorDeposito = null;

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.depositar(valorDeposito));
    }

    @Test
    void naoDevePermitirDepositoNegativo() {
        // Given
        BigDecimal valorDeposito = new BigDecimal("-100.00");

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.depositar(valorDeposito));
    }

    @Test
    void naoDevePermitirDepositoIgualAZero() {
        // Given
        BigDecimal valorDeposito = BigDecimal.ZERO;

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.depositar(valorDeposito));
    }

    @Test
    void deveRealizarSaqueComTarifa() {
        // Given
        BigDecimal valorSaque = new BigDecimal("100.00");
        BigDecimal valorEsperado = new BigDecimal("898.00");

        // When
        conta.sacar(valorSaque);
        BigDecimal valorObtido = conta.getSaldo();

        // Then
        assertEquals(
                0,
                valorEsperado.compareTo(valorObtido));
    }

    @Test
    void naoDevePermitirSaqueComSaldoInsuficiente() {
        // Given
        BigDecimal valorSaque = new BigDecimal("1000.00");
        BigDecimal saldoEsperado = new BigDecimal("1000.00");

        // When
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.sacar(valorSaque));

        // Then
        BigDecimal saldoObtido = conta.getSaldo();

        assertEquals(
                0,
                saldoEsperado.compareTo(saldoObtido));
    }

    @Test
    void devePermitirSaqueQuandoValorMaisTarifaIgualAoSaldo() {
        // Given
        BigDecimal valorSaque = new BigDecimal("998.00");
        BigDecimal saldoEsperado = BigDecimal.ZERO;

        // When
        conta.sacar(valorSaque);
        BigDecimal saldoObtido = conta.getSaldo();

        // Then
        assertEquals(
                0,
                saldoEsperado.compareTo(saldoObtido));
    }

    @Test
    void naoDevePermitirSaqueNulo() {
        // Given
        BigDecimal valorSaque = null;

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.sacar(valorSaque));
    }

    @Test
    void naoDevePermitirSaqueNegativo() {
        // Given
        BigDecimal valorSaque = new BigDecimal("-50.00");

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.sacar(valorSaque));
    }

    @Test
    void naoDevePermitirSaqueIgualAZero() {
        // Given
        BigDecimal valorSaque = BigDecimal.ZERO;

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.sacar(valorSaque));
    }

}