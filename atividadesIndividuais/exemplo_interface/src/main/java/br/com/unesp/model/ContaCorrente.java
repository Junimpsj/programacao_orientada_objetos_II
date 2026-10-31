package br.com.unesp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Conta corrente com limite de crédito, tarifa por saque e bônus em depósitos
 * que levam o saldo acima de R$ 50.000,00.
 */
public class ContaCorrente extends Conta {

    /**
     * Número da conta.
     */
    private int numConta;
    /**
     * Número da agência.
     */
    private int numAgencia;
    /**
     * Limite de crédito (cheque especial).
     */
    private BigDecimal limite;

    /**
     * Tarifa cobrada a cada saque.
     */
    private static final BigDecimal TARIFA = new BigDecimal("10.00");
    /**
     * Saldo acima do qual o depósito rende bônus.
     */
    private static final BigDecimal LIMITE_BONUS = new BigDecimal("50000.00");
    /**
     * Percentual de bônus sobre o depósito.
     */
    private static final BigDecimal BONUS = new BigDecimal("0.005");

    /**
     * Cria uma conta corrente.
     *
     * @param codigo código da conta
     * @param cliente nome do cliente titular
     * @param saldo saldo inicial
     * @param numConta número da conta
     * @param numAgencia número da agência
     * @param limite limite de crédito
     */
    public ContaCorrente(int codigo, String cliente,
            BigDecimal saldo, int numConta,
            int numAgencia, BigDecimal limite) {

        super(codigo, cliente, saldo);

        this.numConta = numConta;
        this.numAgencia = numAgencia;
        this.limite = limite;
    }

    /**
     * Deposita um valor, com bônus de 0,5% se o saldo final passar de R$ 50.000,00.
     *
     * @param valor valor a depositar
     * @throws IllegalArgumentException se o valor for nulo ou menor/igual a zero
     */
    @Override
    public void depositar(BigDecimal valor) {

        if (valor == null) {
            throw new IllegalArgumentException(
                    "O valor do depósito não pode ser nulo.");
        }

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor do depósito deve ser maior que zero.");
        }

        BigDecimal saldoProjetado = this.saldo.add(valor);

        if (saldoProjetado.compareTo(LIMITE_BONUS) > 0) {
            BigDecimal bonus = valor.multiply(BONUS).setScale(2, RoundingMode.HALF_UP);
            saldoProjetado = saldoProjetado.add(bonus);
        }

        this.saldo = saldoProjetado.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Saca um valor cobrando a tarifa fixa, podendo usar o limite.
     *
     * @param valor valor a sacar
     * @throws IllegalArgumentException se o valor for nulo, menor/igual a zero ou se o saldo com limite não cobrir saque e tarifa
     */
    @Override
    public void sacar(BigDecimal valor) {

        if (valor == null) {
            throw new IllegalArgumentException(
                    "O valor do saque não pode ser nulo.");
        }

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor do saque deve ser maior que zero.");
        }

        BigDecimal total = valor.add(TARIFA);
        BigDecimal saldoDisponivel = this.saldo.add(this.limite);

        if (saldoDisponivel.compareTo(total) < 0) {
            throw new IllegalArgumentException(
                    "Saldo insuficiente para realizar o saque.");
        }

        this.saldo = this.saldo.subtract(total)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Imprime o extrato da conta no console.
     */
    public void imprimirExtrato() {

        System.out.println("\n===== EXTRATO BANCÁRIO =====");
        System.out.println("Código: " + codigo);
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Conta: " + numConta);
        System.out.println("Agência: " + numAgencia);
        System.out.println("Limite: R$ " + limite);
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("============================");
    }
}
