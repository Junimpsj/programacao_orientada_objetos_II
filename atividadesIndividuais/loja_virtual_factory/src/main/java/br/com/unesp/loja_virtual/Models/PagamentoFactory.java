package br.com.unesp.loja_virtual.Models;

import br.com.unesp.loja_virtual.Interface.Pagamento;

/**
 * Factory que centraliza a criação das formas de {@link Pagamento} a partir de um tipo.
 */
public class PagamentoFactory {

    /**
     * Tipo de pagamento via PIX.
     */
    public static final String PIX = "PIX";
    /**
     * Tipo de pagamento via cartão de crédito.
     */
    public static final String CREDITO = "CREDITO";
    /**
     * Tipo de pagamento via cartão de débito.
     */
    public static final String DEBITO = "DEBITO";
    /**
     * Tipo de pagamento via boleto.
     */
    public static final String BOLETO = "BOLETO";

    /**
     * Cria a forma de pagamento correspondente ao tipo informado.
     *
     * @param tipo uma das constantes de tipo desta classe ({@link #PIX}, {@link #CREDITO}, {@link #DEBITO} ou {@link #BOLETO})
     * @return a forma de pagamento correspondente
     * @throws IllegalArgumentException se o tipo não for suportado
     */
    public Pagamento criar(String tipo) {

        if (tipo.equals(PIX)) {
            return new PagamentoPix();

        } else if (tipo.equals(CREDITO)) {
            return new PagamentoCartaoCredito();

        } else if (tipo.equals(DEBITO)) {
            return new PagamentoCartaoDebito();

        } else if (tipo.equals(BOLETO)) {
            return new PagamentoBoleto();

        } else {
            throw new IllegalArgumentException("Tipo de pagamento não suportado");
        }
    }
}