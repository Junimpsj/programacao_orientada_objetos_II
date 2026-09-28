package br.com.unesp.interfaces;

import java.math.BigDecimal;

public interface OperacoesBancarias {

    void depositar(BigDecimal valor);
    void sacar(BigDecimal valor);
}
