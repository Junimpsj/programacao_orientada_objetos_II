package br.com.unesp.polinomio.model;

import java.math.BigDecimal;

import br.com.unesp.polinomio.interfaces.Calculavel;

/**
 * Termo de um polinômio, formado por coeficiente e expoente (ex.: {@code 3x^2}).
 */
public class Termo implements Calculavel {

    /**
     * Coeficiente do termo.
     */
    private BigDecimal coeficiente;
    /**
     * Expoente do termo.
     */
    private int expoente;

    /**
     * Cria um termo.
     *
     * @param coeficiente coeficiente do termo
     * @param expoente expoente do termo, não pode ser negativo
     * @throws IllegalArgumentException se o coeficiente for nulo ou o expoente negativo
     */
    public Termo(BigDecimal coeficiente, int expoente) {

        if (coeficiente == null) {
            throw new IllegalArgumentException("O coeficiente não pode ser nulo.");
        }

        if (expoente < 0) {
            throw new IllegalArgumentException("O expoente não pode ser negativo.");
        }

        this.coeficiente = coeficiente;
        this.expoente = expoente;
    }

    /**
     * Retorna o coeficiente.
     *
     * @return o coeficiente
     */
    public BigDecimal getCoeficiente() {
        return coeficiente;
    }

    /**
     * Retorna o expoente.
     *
     * @return o expoente
     */
    public int getExpoente() {
        return expoente;
    }

    /**
     * Substitui os valores deste termo pelos do termo recebido.
     *
     * @param termo termo cujos valores serão copiados
     * @throws IllegalArgumentException se o termo for nulo
     */
    // substitui os valores do termo corrente pelos do termo recebido
    public void insere(Termo termo) {

        if (termo == null) {
            throw new IllegalArgumentException("O termo não pode ser nulo.");
        }

        this.coeficiente = termo.coeficiente;
        this.expoente = termo.expoente;
    }

    /**
     * Calcula o valor do termo para um x.
     *
     * @param x valor de x
     * @return coeficiente vezes x elevado ao expoente
     * @throws IllegalArgumentException se x for nulo
     */
    @Override
    public BigDecimal calcula(BigDecimal x) {

        if (x == null) {
            throw new IllegalArgumentException("O valor de x não pode ser nulo.");
        }

        // x elevado a 0 sempre tem que ser 1, mesmo com x zerado
        // parece que o BigDecimal.pow trata isso certo já
        return coeficiente.multiply(x.pow(expoente));
    }

    /**
     * Representa o termo no formato {@code coeficientex^expoente}.
     *
     * @return a representação textual do termo
     */
    // monta 3x^2, 5x^1 ou 2x^0 pra usar na impressão do polinômio
    public String toStringTermo() {
        return coeficiente + "x^" + expoente;
    }
}
