import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.jogo_bridge.Aplication.App;
import br.com.unesp.jogo_bridge.Bridge.Guerreiro;
import br.com.unesp.jogo_bridge.Bridge.Mago;
import br.com.unesp.jogo_bridge.Models.Arco;
import br.com.unesp.jogo_bridge.Models.Espada;

/**
 * Testes do padrão Bridge: combinação de personagens e armas e troca em tempo de execução.
 */
public class JogoBridgeTest {

    /**
     * Captura o que for impresso no console durante a ação.
     *
     * @param acao trecho de código a ser executado
     * @return a saída do console, sem espaços nas pontas
     */
    // captura o System.out de qualquer trecho, mesma ideia do CheckoutTest
    private String capturarSaida(Runnable acao) {
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(saida));

        try {
            acao.run();
        } finally {
            System.setOut(original);
        }

        return saida.toString().trim().replace("\r\n", "\n");
    }

    /**
     * Verifica o ataque de um guerreiro equipado com espada.
     */
    @Test
    void guerreiroDeveAtacarComEspada() {

        // given
        Guerreiro guerreiro = new Guerreiro(new Espada());

        // when
        String saida = capturarSaida(guerreiro::atacar);

        // then
        assertEquals("""
                Guerreiro realiza um ataque!
                Ataque executado com a espada!""", saida);

    }

    /**
     * Verifica que o mago troca de arma e passa a atacar com a nova.
     */
    @Test
    void deveTrocarDeArmaEmTempoDeExecucao() {

        // given
        Mago mago = new Mago(new Espada());

        // when
        String saida = capturarSaida(() -> {
            mago.equiparArma(new Arco());
            mago.atacar();
        });

        // then
        assertEquals("""
                Mago equipa um Arco.
                Mago realiza um ataque!
                Ataque executado com o arco!""", saida);

    }

    /**
     * Verifica a saída completa de {@link App#main(String[])}.
     */
    @Test
    void deveExecutarSimulacaoCompleta() {

        // when
        String saida = capturarSaida(() -> App.main(new String[0]));

        // then
        assertEquals("""
                === INÍCIO DA PARTIDA ===
                Personagem: Guerreiro
                Arma: Espada
                Guerreiro realiza um ataque!
                Ataque executado com a espada!
                Personagem: Mago
                Arma: Cajado
                Mago realiza um ataque!
                Ataque mágico executado com o cajado!
                === TROCA DE ARMAS ===
                Guerreiro equipa um Arco.
                Guerreiro realiza um ataque!
                Ataque executado com o arco!
                Mago equipa uma Espada.
                Mago realiza um ataque!
                Ataque executado com a espada!
                Unespiano realiza um ataque.
                Ataque executado com o caramelo!
                === FIM DA SIMULAÇÃO ===""", saida);

    }
}
