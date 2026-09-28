package br.com.unesp.app;

import java.math.BigDecimal;

import br.com.unesp.model.ContaPoupanca;

public class App {

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