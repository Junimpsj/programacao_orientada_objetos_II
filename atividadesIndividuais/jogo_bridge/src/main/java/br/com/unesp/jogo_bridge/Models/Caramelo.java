package br.com.unesp.jogo_bridge.Models;

import br.com.unesp.jogo_bridge.Interface.Arma;

/**
 * Arma concreta: caramelo.
 */
public class Caramelo implements Arma {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNome() {
        return "Caramelo";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNomeComArtigo() {
        return "um Caramelo";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void atacar() {
        System.out.println("Ataque executado com o caramelo!");
    }
}
