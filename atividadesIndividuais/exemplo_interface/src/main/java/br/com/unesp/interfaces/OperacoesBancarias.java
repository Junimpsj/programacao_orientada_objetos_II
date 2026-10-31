package br.com.unesp.interfaces;

import java.math.BigDecimal;

/**
 * Operações bancárias básicas que toda conta deve oferecer.
 */
public interface OperacoesBancarias {

    /**
     * Deposita um valor na conta.
     *
     * @param valor valor a depositar
     */
    void depositar(BigDecimal valor);
    /**
     * Saca um valor da conta.
     *
     * @param valor valor a sacar
     */
    void sacar(BigDecimal valor);
}
