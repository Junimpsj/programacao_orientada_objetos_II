package br.com.unesp.avaliacao;

/**
 * Participante do concurso de vendas.
 */
public class Participante {

    /**
     * Nome do participante.
     */
    private String nome;
    /**
     * Categoria do participante.
     */
    // 1 - Padrão , 2 - Sênior
    private int categoria; 

    /**
     * Cria um participante.
     *
     * @param nome nome do participante
     * @param categoria categoria: 1 (Padrão) ou 2 (Sênior)
     * @throws IllegalStateException se a categoria não for 1 nem 2
     */
    public Participante(String nome, int categoria) {

        this.nome = nome;

        if(categoria < 1 || categoria > 2) {

            throw new IllegalStateException("Opção de categoria errada!");
        }
        
        this.categoria = categoria;
    }

    /**
     * Retorna a categoria do participante.
     *
     * @return 1 para Padrão ou 2 para Sênior
     */
    public int getCategoria() {

        return this.categoria;
    }

}