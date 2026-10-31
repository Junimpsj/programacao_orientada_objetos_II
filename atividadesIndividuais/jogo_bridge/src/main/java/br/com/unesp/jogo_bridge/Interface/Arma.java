package br.com.unesp.jogo_bridge.Interface;

/**
 * Implementor do Bridge: define o contrato de uma arma utilizável por qualquer personagem.
 */
// implementor do bridge, cada arma sabe a sua própria forma de atacar
public interface Arma {
    /**
     * Retorna o nome da arma.
     *
     * @return o nome da arma
     */
    String getNome();
    /**
     * Retorna o nome da arma precedido do artigo indefinido (ex.: "uma Espada").
     *
     * @return o nome da arma com artigo
     */
    String getNomeComArtigo();
    /**
     * Executa o ataque característico da arma.
     */
    void atacar();
}
