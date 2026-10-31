package br.com.unesp.jogo_bridge.Aplication;

import br.com.unesp.jogo_bridge.Bridge.Guerreiro;
import br.com.unesp.jogo_bridge.Bridge.Mago;
import br.com.unesp.jogo_bridge.Bridge.Unespiano;
import br.com.unesp.jogo_bridge.Models.Arco;
import br.com.unesp.jogo_bridge.Models.Cajado;
import br.com.unesp.jogo_bridge.Models.Caramelo;
import br.com.unesp.jogo_bridge.Models.Espada;

/**
 * Ponto de entrada que simula uma partida demonstrando o padrão Bridge:
 * personagens e armas são combinados e trocados em tempo de execução.
 */
public class App {

    /**
     * Executa a simulação de partida, incluindo a troca de armas.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=== INÍCIO DA PARTIDA ===");

        Guerreiro guerreiro = new Guerreiro(new Espada());
        guerreiro.apresentar();
        guerreiro.atacar();

        Mago mago = new Mago(new Cajado());
        mago.apresentar();
        mago.atacar();

        System.out.println("=== TROCA DE ARMAS ===");

        guerreiro.equiparArma(new Arco());
        guerreiro.atacar();

        mago.equiparArma(new Espada());
        mago.atacar();

        new Unespiano(new Caramelo()).atacar();

        System.out.println("=== FIM DA SIMULAÇÃO ===");
    }
}
