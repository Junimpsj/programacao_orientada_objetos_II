package br.com.unesp.app;

import java.math.BigDecimal;

import br.com.unesp.model.ContaPoupanca;

/**
 * Ponto de entrada que exercita uma {@link ContaPoupanca} com depósitos e saques.
 */
public class App {

    /**
     * Realiza depósitos (com bônus normal e reduzido) e saques (válido e inválido), imprimindo o extrato.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {

        ContaPoupanca conta = new ContaPoupanca(
                1,
                "João da Silva",
                new BigDecimal("4500.00"),
                12345,
                123,
                new BigDecimal("0.005")
        );

        conta.imprimirExtrato();

        // Depósito com bônus de 1%
        conta.depositar(new BigDecimal("200.00"));
        conta.imprimirExtrato();

        // Depósito com bônus reduzido
        conta.depositar(new BigDecimal("500.00"));
        conta.imprimirExtrato();

        // Saque válido
        conta.sacar(new BigDecimal("100.00"));
        conta.imprimirExtrato();

        // Saque inválido
        conta.sacar(new BigDecimal("10000.00"));
        conta.imprimirExtrato();
    }
}