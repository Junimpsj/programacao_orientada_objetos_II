package br.com.unesp.jogo_bridge.Models;

import br.com.unesp.jogo_bridge.Interface.Arma;

/**
 * Arma concreta: espada.
 */
public class Espada implements Arma {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNome() {
        return "Espada";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNomeComArtigo() {
        return "uma Espada";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void atacar() {
        System.out.println("Ataque executado com a espada!");
    }
}
