package br.com.unesp.model;

import java.math.BigDecimal;

import br.com.unesp.interfaces.OperacoesBancarias;

public abstract class Conta implements OperacoesBancarias {

    protected int codigo;
    protected String nomeCliente;
    protected BigDecimal saldo;

    public Conta(int codigo, String cliente, BigDecimal saldo) {
        this.codigo = codigo;
        this.nomeCliente = cliente;
        this.saldo = saldo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setCliente(String cliente) {
        this.nomeCliente = cliente;
    }

    public String getCliente() {
        return nomeCliente;
    }

    public int getCodigo() {
        return codigo;
    }
}
