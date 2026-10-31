package br.com.unesp.jogo_bridge.Bridge;

import br.com.unesp.jogo_bridge.Interface.Arma;

/**
 * Abstração do Bridge: um personagem que mantém uma {@link Arma} por composição,
 * permitindo variar personagem e arma de forma independente.
 */
// abstração do bridge, guarda a arma por composição então personagem e arma variam sem criar classe por combinação
public abstract class Personagem {

    protected final String nome;
    protected Arma arma;

    /**
     * Cria um personagem.
     *
     * @param nome nome do personagem
     * @param arma arma inicial do personagem
     */
    public Personagem(String nome, Arma arma) {
        this.nome = nome;
        this.arma = arma;
    }

    /**
     * Troca a arma do personagem e informa a troca no console.
     *
     * @param arma nova arma a ser equipada
     */
    public void equiparArma(Arma arma) {
        this.arma = arma;
        System.out.println(nome + " equipa " + arma.getNomeComArtigo() + ".");
    }

    /**
     * Imprime no console o nome do personagem e da arma equipada.
     */
    public void apresentar() {
        System.out.println("Personagem: " + nome);
        System.out.println("Arma: " + arma.getNome());
    }

    /**
     * Realiza o ataque do personagem com a arma equipada.
     */
    public abstract void atacar();
}
