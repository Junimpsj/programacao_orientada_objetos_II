package br.com.unesp.jogo_bridge.Bridge;

import br.com.unesp.jogo_bridge.Interface.Arma;

/**
 * Personagem guerreiro, refined abstraction do Bridge.
 */
public class Guerreiro extends Personagem {

    /**
     * Cria um guerreiro.
     *
     * @param arma arma inicial do guerreiro
     */
    public Guerreiro(Arma arma) {
        super("Guerreiro", arma);
    }

    /**
     * Realiza o ataque do guerreiro delegando o golpe à arma equipada.
     */
    @Override
    public void atacar() {
        System.out.println(nome + " realiza um ataque!");
        arma.atacar();
    }
}
