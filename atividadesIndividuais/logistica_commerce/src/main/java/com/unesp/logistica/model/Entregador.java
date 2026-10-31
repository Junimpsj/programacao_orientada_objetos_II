package com.unesp.logistica.model;

/**
 * Entregador da empresa. Cada tipo de veículo calcula o seu bônus.
 */
public abstract class Entregador {

    /**
     * Nome do entregador.
     */
    private String nome;
    /**
     * CPF do entregador.
     */
    private String cpf;
    /**
     * Placa do veículo utilizado.
     */
    private String placaVeiculo;

    /**
     * Cria um entregador.
     *
     * @param nome nome do entregador
     * @param cpf CPF do entregador
     * @param placaVeiculo placa do veículo
     * @throws IllegalArgumentException se algum parâmetro for nulo
     */
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

    /**
     * Retorna o nome do entregador.
     *
     * @return o nome do entregador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o CPF do entregador.
     *
     * @return o CPF do entregador
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna a placa do veículo.
     *
     * @return a placa do veículo
     */
    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    /**
     * Calcula o bônus do entregador.
     *
     * @return o valor do bônus em reais
     */
    public abstract double calcularBonus();

}
