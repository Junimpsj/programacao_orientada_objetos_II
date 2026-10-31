import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.unesp.model.ContaPoupanca;

/**
 * Testes de depósito e saque da {@link ContaPoupanca}.
 */
class ContaPoupancaTest {

    /**
     * Conta usada nos testes.
     */
    private ContaPoupanca conta;

    /**
     * Cria a conta de teste antes de cada teste.
     */
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

    /**
     * Verifica que o depósito rende bônus de 1% enquanto o saldo projetado não passa do limite.
     */
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

    /**
     * Verifica que o bônus é reduzido quando o saldo projetado ultrapassa o limite.
     */
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

    /**
     * Verifica que o bônus normal ainda vale quando o saldo projetado é igual ao limite.
     */
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

    /**
     * Verifica que depósito nulo lança {@link IllegalArgumentException}.
     */
    @Test
    void naoDevePermitirDepositoNulo() {
        // Given
        BigDecimal valorDeposito = null;

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.depositar(valorDeposito));
    }

    /**
     * Verifica que depósito negativo lança {@link IllegalArgumentException}.
     */
    @Test
    void naoDevePermitirDepositoNegativo() {
        // Given
        BigDecimal valorDeposito = new BigDecimal("-100.00");

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.depositar(valorDeposito));
    }

    /**
     * Verifica que depósito igual a zero lança {@link IllegalArgumentException}.
     */
    @Test
    void naoDevePermitirDepositoIgualAZero() {
        // Given
        BigDecimal valorDeposito = BigDecimal.ZERO;

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.depositar(valorDeposito));
    }

    /**
     * Verifica que o saque debita o valor mais a tarifa.
     */
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

    /**
     * Verifica que saque acima do saldo lança {@link IllegalArgumentException}.
     */
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

    /**
     * Verifica que o saque é permitido quando valor mais tarifa é igual ao saldo.
     */
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

    /**
     * Verifica que saque nulo lança {@link IllegalArgumentException}.
     */
    @Test
    void naoDevePermitirSaqueNulo() {
        // Given
        BigDecimal valorSaque = null;

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.sacar(valorSaque));
    }

    /**
     * Verifica que saque negativo lança {@link IllegalArgumentException}.
     */
    @Test
    void naoDevePermitirSaqueNegativo() {
        // Given
        BigDecimal valorSaque = new BigDecimal("-50.00");

        // When / Then
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.sacar(valorSaque));
    }

    /**
     * Verifica que saque igual a zero lança {@link IllegalArgumentException}.
     */
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