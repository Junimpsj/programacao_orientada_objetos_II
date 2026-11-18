package br.com.unesp.mapa_parque_flyweight.Models;

import java.util.ArrayList;
import java.util.List;

import br.com.unesp.mapa_parque_flyweight.Flyweight.ArvoreFactory;

/**
 * Cliente do Flyweight: cadastra as árvores do parque pedindo os tipos à
 * {@link ArvoreFactory}, exibe as árvores e o resumo de objetos criados.
 */
public class MapaParque {

    private final ArvoreFactory factory = new ArvoreFactory();
    private final List<Arvore> arvores = new ArrayList<>();

    /**
     * Cadastra uma árvore na posição informada, reutilizando o tipo se ele já existir.
     *
     * @param x posição horizontal no mapa
     * @param y posição vertical no mapa
     * @param especie espécie da árvore
     * @param formato formato da copa
     * @param cor cor predominante
     * @throws IllegalArgumentException se a posição for negativa ou alguma característica vazia
     */
    public void cadastrarArvore(int x, int y, String especie, String formato, String cor) {
        arvores.add(new Arvore(x, y, factory.obterTipo(especie, formato, cor)));
    }

    /**
     * Imprime todas as árvores do mapa com suas posições, na ordem de cadastro.
     */
    public void exibirArvores() {

        for (int i = 0; i < arvores.size(); i++) {

            if (i > 0) {
                System.out.println();
            }

            System.out.println("Árvore " + (i + 1) + ":");
            arvores.get(i).exibir();
        }
    }

    /**
     * Imprime o total de árvores, os objetos criados e os reutilizados.
     */
    // toda ocorrência que não gerou objeto novo reaproveitou um existente, por isso a subtração
    public void exibirResumo() {
        System.out.println("Total de árvores no mapa: " + getTotalArvores());
        System.out.println("Objetos de árvores criados: " + getObjetosCriados());
        System.out.println("Objetos compartilhados/reutilizados: " + (getTotalArvores() - getObjetosCriados()));
    }

    /**
     * Retorna quantas árvores existem no mapa.
     *
     * @return o total de ocorrências
     */
    public int getTotalArvores() {
        return arvores.size();
    }

    /**
     * Retorna quantos objetos de tipo de árvore foram efetivamente criados.
     *
     * @return a quantidade de objetos criados
     */
    public int getObjetosCriados() {
        return factory.getQuantidadeTipos();
    }
}
