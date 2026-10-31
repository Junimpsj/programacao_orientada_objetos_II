package br.com.unesp.polinomio.interfaces;

import java.math.BigDecimal;

/**
 * Contrato de expressões matemáticas que podem ser calculadas para um valor de x.
 */
public interface Calculavel {
    /**
     * Calcula o valor da expressão.
     *
     * @param x valor de x
     * @return o resultado para o x informado
     */
    BigDecimal calcula(BigDecimal x);
}
