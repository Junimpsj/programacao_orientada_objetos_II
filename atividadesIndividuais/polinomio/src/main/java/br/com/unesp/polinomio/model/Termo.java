package br.com.unesp.polinomio.model;

import java.math.BigDecimal;

import br.com.unesp.polinomio.interfaces.Calculavel;

public class Termo implements Calculavel {

    private BigDecimal coeficiente;
    private int expoente;

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

    public BigDecimal getCoeficiente() {
        return coeficiente;
    }

    public int getExpoente() {
        return expoente;
    }

    // substitui os valores do termo corrente pelos do termo recebido
    public void insere(Termo termo) {

        if (termo == null) {
            throw new IllegalArgumentException("O termo não pode ser nulo.");
        }

        this.coeficiente = termo.coeficiente;
        this.expoente = termo.expoente;
    }

    @Override
    public BigDecimal calcula(BigDecimal x) {

        if (x == null) {
            throw new IllegalArgumentException("O valor de x não pode ser nulo.");
        }

        // x elevado a 0 sempre tem que ser 1, mesmo com x zerado
        // parece que o BigDecimal.pow trata isso certo já
        return coeficiente.multiply(x.pow(expoente));
    }

    // monta 3x^2, 5x^1 ou 2x^0 pra usar na impressão do polinômio
    public String toStringTermo() {
        return coeficiente + "x^" + expoente;
    }
}
