package com.unesp.logistica.model;

/**
 * Entregador de moto: o bônus é proporcional à meta e ganha valor fixo ao atingi-la.
 */
public class EntregadorMoto extends Entregador {

    /**
     * Bônus fixo pago ao atingir a meta.
     */
    private static final double BONUS_FIXO_META_ATINGIDA = 300.00;
    /**
     * Percentual mínimo da meta para receber o bônus fixo.
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
     * Cria um entregador de moto.
     *
     * @param nome nome do entregador
     * @param cpf CPF do entregador
     * @param placaVeiculo placa da moto
     * @param taxaBase taxa base, deve ser positiva
     * @param percentualDaMeta percentual da meta atingido, não pode ser negativo
     * @throws IllegalArgumentException se a taxa base não for positiva ou o percentual for negativo
     */
    public EntregadorMoto(String nome, String cpf, String placaVeiculo, double taxaBase, double percentualDaMeta) {

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
     * Calcula o bônus: taxa base vezes o percentual da meta, mais o valor fixo se a meta for atingida.
     *
     * @return o valor do bônus em reais
     */
    @Override
    public double calcularBonus() {

        double bonus = taxaBase * percentualDaMeta;

        if(percentualDaMeta >= META_MINIMA) {
            bonus += BONUS_FIXO_META_ATINGIDA;
        }

        return bonus;

    }

}