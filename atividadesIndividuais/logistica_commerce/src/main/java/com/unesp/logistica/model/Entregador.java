package com.unesp.logistica.model;

public abstract class Entregador {

    private String nome;
    private String cpf;
    private String placaVeiculo;

    public Entregador(String nome, String cpf, String placaVeiculo) {
        
    if(nome == null) {
        throw new IllegalArgumentException("Nome não pode ser vazio.");
    }

    if(cpf == null) {
        throw new IllegalArgumentException("CPF não pode ser vazio.");
    }

    if(placaVeiculo == null) {
        throw new IllegalArgumentException("Placa do veículo não pode ser vazia.");
    }

        this.nome = nome;
        this.cpf = cpf;
        this.placaVeiculo = placaVeiculo;
        
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public abstract double calcularBonus();

}
