import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.unesp.model.ContaCorrente;

/**
 * Testes de depósito e saque da {@link ContaCorrente}.
 */
class ContaCorrenteTest {

    /**
     * Conta usada nos testes.
     */
    private ContaCorrente conta;

    /**
     * Cria a conta de teste antes de cada teste.
     */
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

    /**
     * Verifica que o depósito não rende bônus enquanto o saldo projetado não passa de R$ 50.000,00.
     */
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

    /**
     * Verifica que o depósito rende bônus quando o saldo projetado passa de R$ 50.000,00.
     */
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

    /**
     * Verifica que não há bônus quando o saldo projetado é exatamente o limite.
     */
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
     * Verifica que o saque dentro do saldo debita o valor mais a tarifa.
     */
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

    /**
     * Verifica que o saque pode usar o limite quando o saldo não basta.
     */
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

    /**
     * Verifica que saque acima de saldo mais limite lança {@link IllegalArgumentException}.
     */
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
