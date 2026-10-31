package com.unesp.logistica.model;

/**
 * Entregador de bicicleta: o bônus é proporcional aos dias úteis em que a meta foi batida.
 */
public class EntregadorBike extends Entregador {

    /**
     * Total de dias úteis do mês.
     */
    private static final int TOTAL_DIAS_UTEIS = 22;
    /**
     * Mínimo de dias de meta batida para ter direito ao bônus.
     */
    private static final int DIAS_MINIMOS_PARA_BONUS = 11;

    /**
     * Taxa base usada no cálculo do bônus.
     */
    private double taxaBase;
    /**
     * Quantidade de dias em que a meta foi batida.
     */
    private int diasMetaBatida;

    /**
     * Cria um entregador de bicicleta (que não possui placa, portanto fixada em "N/A").
     *
     * @param nome nome do entregador
     * @param cpf CPF do entregador
     * @param placaVeiculo ignorado, bicicletas não possuem placa
     * @param taxaBase taxa base, deve ser positiva
     * @param diasMetaBatida dias de meta batida, entre 0 e 22
     * @throws IllegalArgumentException se a taxa base não for positiva ou os dias estiverem fora do intervalo
     */
    public EntregadorBike(String nome, String cpf, String placaVeiculo, double taxaBase, int diasMetaBatida) {

        super(nome, cpf, "N/A");

    if(taxaBase <= 0) {
        throw new IllegalArgumentException("Taxa base deve ser positiva.");
    }

    if(diasMetaBatida < 0 || diasMetaBatida > TOTAL_DIAS_UTEIS) {
        throw new IllegalArgumentException("Dias de meta batida deve estar entre 0 e " + TOTAL_DIAS_UTEIS + ".");
    }

        this.taxaBase = taxaBase;
        this.diasMetaBatida = diasMetaBatida;

    }

    /**
     * Retorna a taxa base.
     *
     * @return a taxa base
     */
    public double getTaxaBase() {
        return taxaBase;
    }

    /**
     * Retorna os dias em que a meta foi batida.
     *
     * @return os dias de meta batida
     */
    public int getDiasMetaBatida() {
        return diasMetaBatida;
    }

    /**
     * Calcula o bônus proporcional aos dias de meta batida; zero se forem menos de 11.
     *
     * @return o valor do bônus em reais
     */
    @Override
    public double calcularBonus() {

        if(diasMetaBatida < DIAS_MINIMOS_PARA_BONUS) {
            return 0.0;
        }

        double proporcao = (double) diasMetaBatida / TOTAL_DIAS_UTEIS;
        return taxaBase * proporcao;

    }

}