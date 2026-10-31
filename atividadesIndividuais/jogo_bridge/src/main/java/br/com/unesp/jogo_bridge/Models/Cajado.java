package br.com.unesp.jogo_bridge.Models;

import br.com.unesp.jogo_bridge.Interface.Arma;

/**
 * Arma concreta: cajado, usado em ataques mágicos.
 */
public class Cajado implements Arma {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNome() {
        return "Cajado";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNomeComArtigo() {
        return "um Cajado";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void atacar() {
        System.out.println("Ataque mágico executado com o cajado!");
    }
}
