package br.com.unesp.mapa_parque_flyweight.Flyweight;

/**
 * Flyweight: guarda o estado intrínseco da árvore (espécie, formato e cor),
 * compartilhado por todas as árvores com as mesmas características.
 */
// flyweight, imutável (tudo final e sem setter) porque o mesmo objeto é usado por várias árvores do mapa
public class TipoArvore {

    private final String especie;
    private final String formato;
    private final String cor;

    /**
     * Cria o tipo de árvore. Deve ser chamado apenas pela {@link ArvoreFactory}.
     *
     * @param especie espécie da árvore
     * @param formato formato da copa
     * @param cor cor predominante
     * @throws IllegalArgumentException se algum dos dados for nulo ou vazio
     */
    TipoArvore(String especie, String formato, String cor) {

        if (especie == null || especie.isBlank() || formato == null || formato.isBlank() || cor == null || cor.isBlank()) {
            throw new IllegalArgumentException("Espécie, formato e cor da árvore não podem ser vazios.");
        }

        this.especie = especie;
        this.formato = formato;
        this.cor = cor;
    }

    /**
     * Imprime a árvore na posição informada.
     *
     * @param x posição horizontal no mapa (estado extrínseco)
     * @param y posição vertical no mapa (estado extrínseco)
     */
    // a posição vem de fora (estado extrínseco), o flyweight não guarda nada que muda de árvore pra árvore
    public void exibir(int x, int y) {
        System.out.println("Espécie: " + especie);
        System.out.println("Posição: (" + x + ", " + y + ")");
    }

    /**
     * Retorna a espécie, o formato e a cor no formato usado no cadastro.
     *
     * @return as características da árvore
     */
    @Override
    public String toString() {
        return "Espécie: " + especie + " | Formato: " + formato + " | Cor: " + cor;
    }
}
