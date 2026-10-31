package br.com.unesp.model;

import java.math.BigDecimal;

import br.com.unesp.interfaces.OperacoesBancarias;

/**
 * Conta bancária base com código, cliente e saldo.
 */
public abstract class Conta implements OperacoesBancarias {

    /**
     * Código da conta.
     */
    protected int codigo;
    /**
     * Nome do cliente titular.
     */
    protected String nomeCliente;
    /**
     * Saldo atual da conta.
     */
    protected BigDecimal saldo;

    /**
     * Cria uma conta.
     *
     * @param codigo código da conta
     * @param cliente nome do cliente titular
     * @param saldo saldo inicial
     */
    public Conta(int codigo, String cliente, BigDecimal saldo) {
        this.codigo = codigo;
        this.nomeCliente = cliente;
        this.saldo = saldo;
    }

    /**
     * Retorna o saldo atual.
     *
     * @return o saldo da conta
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * Define o cliente titular.
     *
     * @param cliente novo nome do cliente
     */
    public void setCliente(String cliente) {
        this.nomeCliente = cliente;
    }

    /**
     * Retorna o cliente titular.
     *
     * @return o nome do cliente
     */
    public String getCliente() {
        return nomeCliente;
    }

    /**
     * Retorna o código da conta.
     *
     * @return o código da conta
     */
    public int getCodigo() {
        return codigo;
    }
}
