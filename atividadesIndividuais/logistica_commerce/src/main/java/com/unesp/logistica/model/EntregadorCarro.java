package com.unesp.logistica.model;

public class EntregadorCarro extends Entregador {

    private static final double PESO_FROTA = 1.4;
    private static final double BONUS_FIXO_FROTA = 600.00;
    private static final double META_MINIMA = 1.0;

    private double taxaBase;
    private double percentualDaMeta;

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

    public double getTaxaBase() {
        return taxaBase;
    }

    public double getPercentualDaMeta() {
        return percentualDaMeta;
    }

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