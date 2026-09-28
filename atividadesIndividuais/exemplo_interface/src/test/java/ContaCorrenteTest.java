import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.unesp.model.ContaCorrente;

class ContaCorrenteTest {

    private ContaCorrente conta;

    @BeforeEach
    void setUp() {
        conta = new ContaCorrente(
                1,
                "João Potter da Silva",
                new BigDecimal("1000.00"),
                12345,
                123,
                new BigDecimal("500.00"));
    }

    @Test
    void deveRealizarDepositoSemBonusAbaixoDoLimite() {
        // Given
        BigDecimal valorDeposito = new BigDecimal("100.00");
        BigDecimal valorEsperado = new BigDecimal("1100.00");

        // When
        conta.depositar(valorDeposito);
        BigDecimal valorObtido = conta.getSaldo();

        // Then
        assertEquals(
                0,
                valorEsperado.compareTo(valorObtido));
    }

    @Test
    void deveAplicarBonusAoUltrapassarCinquentaMil() {
        // Given
        conta = new ContaCorrente(
                1,
                "João",
                new BigDecimal("49900.00"),
                12345,
                123,
                new BigDecimal("500.00"));

        BigDecimal valorDeposito = new BigDecimal("200.00");
        // saldoProjetado = 50100.00 > 50000.00 -> bonus = 200 * 0.005 = 1.00
        BigDecimal valorEsperado = new BigDecimal("50101.00");

        // When
        conta.depositar(valorDeposito);
        BigDecimal valorObtido = conta.getSaldo();

        // Then
        assertEquals(
                0,
                valorEsperado.compareTo(valorObtido));
    }

    @Test
    void naoDeveAplicarBonusQuandoSaldoProjetadoForIgualAoLimite() {
        // Given
        conta = new ContaCorrente(
                1,
                "João",
                new BigDecimal("49900.00"),
                12345,
                123,
                new BigDecimal("500.00"));

        BigDecimal valorDeposito = new BigDecimal("100.00");
        BigDecimal valorEsperado = new BigDecimal("50000.00");

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
    void deveRealizarSaqueComTarifaUsandoSaldo() {
        // Given
        BigDecimal valorSaque = new BigDecimal("100.00");
        BigDecimal valorEsperado = new BigDecimal("890.00");

        // When
        conta.sacar(valorSaque);
        BigDecimal valorObtido = conta.getSaldo();

        // Then
        assertEquals(
                0,
                valorEsperado.compareTo(valorObtido));
    }

    @Test
    void devePermitirSaqueUsandoLimite() {
        // Given
        // saldo 1000 + limite 500 = 1400 disponível (1000 - tarifa 10 = 990 max valor de saque s/ limite)
        BigDecimal valorSaque = new BigDecimal("1400.00");
        BigDecimal saldoEsperado = new BigDecimal("-410.00");

        // When
        conta.sacar(valorSaque);
        BigDecimal saldoObtido = conta.getSaldo();

        // Then
        assertEquals(
                0,
                saldoEsperado.compareTo(saldoObtido));
    }

    @Test
    void naoDevePermitirSaqueComSaldoMaisLimiteInsuficiente() {
        // Given
        BigDecimal valorSaque = new BigDecimal("2000.00");
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
