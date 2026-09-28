package br.com.unesp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaCorrente extends Conta {

    private int numConta;
    private int numAgencia;
    private BigDecimal limite;

    private static final BigDecimal TARIFA = new BigDecimal("10.00");
    private static final BigDecimal LIMITE_BONUS = new BigDecimal("50000.00");
    private static final BigDecimal BONUS = new BigDecimal("0.005");

    public ContaCorrente(int codigo, String cliente,
            BigDecimal saldo, int numConta,
            int numAgencia, BigDecimal limite) {

        super(codigo, cliente, saldo);

        this.numConta = numConta;
        this.numAgencia = numAgencia;
        this.limite = limite;
    }

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
