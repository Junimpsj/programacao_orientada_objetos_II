package br.com.unesp.avaliacao;

public class Participante {

    private String nome;
    // 1 - Padrão , 2 - Sênior
    private int categoria; 

    public Participante(String nome, int categoria) {

        this.nome = nome;

        if(categoria < 1 || categoria > 2) {

            throw new IllegalStateException("Opção de categoria errada!");
        }
        
        this.categoria = categoria;
    }

    public int getCategoria() {

        return this.categoria;
    }

}