package br.com.unesp.jogo_bridge.Models;

import br.com.unesp.jogo_bridge.Interface.Arma;

/**
 * Arma concreta: arco.
 */
public class Arco implements Arma {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNome() {
        return "Arco";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNomeComArtigo() {
        return "um Arco";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void atacar() {
        System.out.println("Ataque executado com o arco!");
    }
}
