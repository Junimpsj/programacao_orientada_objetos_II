package br.com.unesp.jogo_bridge.Bridge;

import br.com.unesp.jogo_bridge.Interface.Arma;

/**
 * Personagem mago, refined abstraction do Bridge.
 */
public class Mago extends Personagem {

    /**
     * Cria um mago.
     *
     * @param arma arma inicial do mago
     */
    public Mago(Arma arma) {
        super("Mago", arma);
    }

    /**
     * Realiza o ataque do mago delegando o golpe à arma equipada.
     */
    @Override
    public void atacar() {
        System.out.println(nome + " realiza um ataque!");
        arma.atacar();
    }
}
