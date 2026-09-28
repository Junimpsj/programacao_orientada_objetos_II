package br.com.unesp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaPoupanca extends Conta {

    private int numConta;
    private int numAgencia;
    private BigDecimal rendimento;

    // usando BigDecimal em vez de double porque o double tem aqueles erros de arredondamento binário
    // e como estamos mexendo com dinheiro isso não pode acontecer
    // obs: criamos a partir de String ("5000.00") pra ele já nascer com o valor exato
    private static final BigDecimal LIMITE = new BigDecimal("5000.00");
    private static final BigDecimal BONUS_NORMAL = new BigDecimal("0.01");
    private static final BigDecimal BONUS_REDUZIDO = new BigDecimal("0.005");
    private static final BigDecimal TARIFA = new BigDecimal("2.00");

    public ContaPoupanca(int codigo, String cliente,
            BigDecimal saldo, int numConta,
            int numAgencia, BigDecimal rendimento) {

        super(codigo, cliente, saldo);

        this.numConta = numConta;
        this.numAgencia = numAgencia;
        this.rendimento = rendimento;
    }

    @Override
    public void depositar(BigDecimal valor) {

        // BigDecimal é objeto, então == compara a referência na memória e não o valor
        // aqui pode usar == tranquilo porque só estamos checando se veio null
        if (valor == null) {
            throw new IllegalArgumentException(
                    "O valor do depósito não pode ser nulo.");
        }

        // como não dá pra fazer valor <= 0 direto (não é primitivo), usamos o compareTo
        // ele retorna negativo, zero ou positivo, então <= 0 já barra valor negativo e zero de uma vez
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor do depósito deve ser maior que zero.");
        }

        // calculando como o saldo ficaria depois do depósito, só pra saber qual bônus aplicar
        BigDecimal saldoProjetado = this.saldo.add(valor);

        // se passar do limite o bônus é reduzido
        // se for exatamente igual ao limite ainda ganha o bônus normal (por isso > 0 e não >= 0)
        BigDecimal taxa = saldoProjetado.compareTo(LIMITE) > 0
                ? BONUS_REDUZIDO
                : BONUS_NORMAL;

        // bônus = valor do depósito * taxa, arredondado pra 2 casas
        // HALF_UP é aquele arredondamento que a gente aprende na escola (0.005 vira 0.01)
        BigDecimal bonus = valor.multiply(taxa).setScale(2, RoundingMode.HALF_UP);

        // BigDecimal é imutável, o add não altera o saldo, ele retorna um novo BigDecimal
        // por isso temos que fazer saldo = saldo.add(...), se chamar só saldo.add(...) não acontece nada
        // o setScale no final garante que o saldo fique sempre com 2 casas decimais
        saldo = saldo.add(valor).add(bonus).setScale(2, RoundingMode.HALF_UP);
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

        // o total do saque é o valor pedido + a tarifa fixa
        BigDecimal total = valor.add(TARIFA);

        // mesma lógica do compareTo lá do depositar, se der < 0 o saldo é menor que o total e não dá pra sacar
        if (this.saldo.compareTo(total) < 0) {
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
        System.out.println("Rendimento: " + rendimento);
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("============================");
    }
}
