package br.com.unesp.jogo_bridge.Bridge;

import br.com.unesp.jogo_bridge.Interface.Arma;

/**
 * Personagem unespiano, refined abstraction do Bridge.
 */
public class Unespiano extends Personagem {

    /**
     * Cria um unespiano.
     *
     * @param arma arma inicial do unespiano
     */
    public Unespiano(Arma arma) {
        super("Unespiano", arma);
    }

    /**
     * Realiza o ataque do unespiano delegando o golpe à arma equipada.
     */
    @Override
    public void atacar() {
        System.out.println(nome + " realiza um ataque.");
        arma.atacar();
    }
}
