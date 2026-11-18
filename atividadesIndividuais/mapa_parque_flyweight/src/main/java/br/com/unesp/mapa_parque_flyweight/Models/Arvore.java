package br.com.unesp.mapa_parque_flyweight.Models;

import br.com.unesp.mapa_parque_flyweight.Flyweight.TipoArvore;

/**
 * Contexto do Flyweight: uma ocorrência de árvore no mapa, com sua posição
 * (estado extrínseco) e uma referência ao {@link TipoArvore} compartilhado.
 */
public class Arvore {

    private final int x;
    private final int y;
    private final TipoArvore tipo;

    /**
     * Cria uma árvore na posição informada.
     *
     * @param x posição horizontal no mapa, não pode ser negativa
     * @param y posição vertical no mapa, não pode ser negativa
     * @param tipo tipo de árvore compartilhado
     * @throws IllegalArgumentException se a posição for negativa ou o tipo nulo
     */
    public Arvore(int x, int y, TipoArvore tipo) {

        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("A posição da árvore não pode ser negativa.");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("O tipo da árvore não pode ser nulo.");
        }

        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    /**
     * Imprime a espécie e a posição da árvore.
     */
    public void exibir() {
        tipo.exibir(x, y);
    }
}
