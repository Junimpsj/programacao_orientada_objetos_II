package br.com.unesp.mapa_parque_flyweight.Aplication;

import br.com.unesp.mapa_parque_flyweight.Models.MapaParque;

/**
 * Ponto de entrada que demonstra o padrão Flyweight: cadastra seis árvores
 * de três tipos e mostra que só três objetos de árvore foram criados.
 */
public class App {

    private static final String LINHA = "----------------------------------------";
    private static final String LINHA_DUPLA = "========================================";

    /**
     * Cadastra as árvores, exibe o mapa e o resumo de objetos.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        MapaParque mapa = new MapaParque();

        System.out.println(LINHA_DUPLA);
        System.out.println("SISTEMA DE MAPA DO PARQUE");
        System.out.println(LINHA_DUPLA);
        System.out.println();

        System.out.println("CADASTRANDO ÁRVORES...");
        System.out.println();
        mapa.cadastrarArvore(10, 20, "Ipê-amarelo", "Copa arredondada", "Amarelo");
        mapa.cadastrarArvore(15, 30, "Jacarandá", "Copa ampla", "Roxo");
        mapa.cadastrarArvore(25, 40, "Ipê-amarelo", "Copa arredondada", "Amarelo");
        mapa.cadastrarArvore(35, 50, "Eucalipto", "Copa estreita", "Verde");
        mapa.cadastrarArvore(45, 60, "Jacarandá", "Copa ampla", "Roxo");
        mapa.cadastrarArvore(55, 70, "Ipê-amarelo", "Copa arredondada", "Amarelo");
        System.out.println();

        System.out.println(LINHA);
        System.out.println("ÁRVORES REGISTRADAS NO MAPA");
        System.out.println(LINHA);
        mapa.exibirArvores();
        System.out.println();

        System.out.println(LINHA);
        System.out.println("RESUMO DO MAPA");
        System.out.println(LINHA);
        System.out.println();
        mapa.exibirResumo();
        System.out.println();
        System.out.println(LINHA_DUPLA);
    }
}
