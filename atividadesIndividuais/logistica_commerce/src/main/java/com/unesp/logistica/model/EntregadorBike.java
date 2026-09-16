package com.unesp.logistica.model;

public class EntregadorBike extends Entregador {

    private static final int TOTAL_DIAS_UTEIS = 22;
    private static final int DIAS_MINIMOS_PARA_BONUS = 11;

    private double taxaBase;
    private int diasMetaBatida;

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

    public double getTaxaBase() {
        return taxaBase;
    }

    public int getDiasMetaBatida() {
        return diasMetaBatida;
    }

    @Override
    public double calcularBonus() {

        if(diasMetaBatida < DIAS_MINIMOS_PARA_BONUS) {
            return 0.0;
        }

        double proporcao = (double) diasMetaBatida / TOTAL_DIAS_UTEIS;
        return taxaBase * proporcao;

    }

}