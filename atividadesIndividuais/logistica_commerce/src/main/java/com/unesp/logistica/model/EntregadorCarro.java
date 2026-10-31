package com.unesp.logistica.model;

/**
 * Entregador de carro: o bônus usa um peso de frota e ganha valor fixo ao atingir a meta.
 */
public class EntregadorCarro extends Entregador {

    /**
     * Peso aplicado ao percentual da meta.
     */
    private static final double PESO_FROTA = 1.4;
    /**
     * Bônus fixo pago ao atingir a meta.
     */
    private static final double BONUS_FIXO_FROTA = 600.00;
    /**
     * Percentual ponderado mínimo para receber o bônus fixo.
     */
    private static final double META_MINIMA = 1.0;

    /**
     * Taxa base usada no cálculo do bônus.
     */
    private double taxaBase;
    /**
     * Percentual da meta atingido (1,0 equivale a 100%).
     */
    private double percentualDaMeta;

    /**
     * Cria um entregador de carro.
     *
     * @param nome nome do entregador
     * @param cpf CPF do entregador
     * @param placaVeiculo placa do carro
     * @param taxaBase taxa base, deve ser positiva
     * @param percentualDaMeta percentual da meta atingido, não pode ser negativo
     * @throws IllegalArgumentException se a taxa base não for positiva ou o percentual for negativo
     */
    public EntregadorCarro(String nome, String cpf, String placaVeiculo, double taxaBase, double percentualDaMeta) {

        super(nome, cpf, placaVeiculo);

    if(taxaBase <= 0) {
        throw new IllegalArgumentException("Taxa base deve ser positiva.");
    }

    if(percentualDaMeta < 0) {
        throw new IllegalArgumentException("Percentual da meta não pode ser negativo.");
    }

        this.taxaBase = taxaBase;
        this.percentualDaMeta = percentualDaMeta;

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
     * Retorna o percentual da meta atingido.
     *
     * @return o percentual da meta
     */
    public double getPercentualDaMeta() {
        return percentualDaMeta;
    }

    /**
     * Calcula o bônus: taxa base vezes o percentual ponderado, mais o valor fixo se a meta ponderada for atingida.
     *
     * @return o valor do bônus em reais
     */
    @Override
    public double calcularBonus() {

        double percentualPonderado = percentualDaMeta * PESO_FROTA;
        double bonus = taxaBase * percentualPonderado;

        if(percentualPonderado >= META_MINIMA) {
            bonus += BONUS_FIXO_FROTA;
        }

        return bonus;

    }

}