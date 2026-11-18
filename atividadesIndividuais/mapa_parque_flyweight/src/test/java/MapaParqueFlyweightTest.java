import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.mapa_parque_flyweight.Aplication.App;
import br.com.unesp.mapa_parque_flyweight.Flyweight.ArvoreFactory;
import br.com.unesp.mapa_parque_flyweight.Flyweight.TipoArvore;
import br.com.unesp.mapa_parque_flyweight.Models.MapaParque;

/**
 * Testes do padrão Flyweight: compartilhamento dos tipos de árvore e contagem de objetos.
 */
public class MapaParqueFlyweightTest {

    /**
     * Captura o que for impresso no console durante a ação.
     *
     * @param acao trecho de código a ser executado
     * @return a saída do console, sem espaços nas pontas
     */
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
     * Verifica que características iguais devolvem o mesmo objeto.
     */
    @Test
    void deveReutilizarTipoComMesmasCaracteristicas() {

        // given
        ArvoreFactory factory = new ArvoreFactory();

        // when
        TipoArvore primeiro = factory.obterTipo("Ipê-amarelo", "Copa arredondada", "Amarelo");
        TipoArvore segundo = factory.obterTipo("Ipê-amarelo", "Copa arredondada", "Amarelo");

        // then
        assertSame(primeiro, segundo);
        assertEquals(1, factory.getQuantidadeTipos());

    }

    /**
     * Verifica que a mesma espécie com outra característica gera um objeto novo.
     */
    @Test
    void deveCriarTipoNovoQuandoCaracteristicaForDiferente() {

        // given
        ArvoreFactory factory = new ArvoreFactory();

        // when
        TipoArvore amarelo = factory.obterTipo("Ipê", "Copa arredondada", "Amarelo");
        TipoArvore roxo = factory.obterTipo("Ipê", "Copa arredondada", "Roxo");

        // then
        assertNotSame(amarelo, roxo);
        assertEquals(2, factory.getQuantidadeTipos());

    }

    /**
     * Verifica a contagem de ocorrências no mapa e de objetos criados.
     */
    @Test
    void deveContarArvoresEObjetosCriados() {

        // given
        MapaParque mapa = new MapaParque();

        // when
        mapa.cadastrarArvore(1, 1, "Eucalipto", "Copa estreita", "Verde");
        mapa.cadastrarArvore(2, 2, "Eucalipto", "Copa estreita", "Verde");
        mapa.cadastrarArvore(3, 3, "Jacarandá", "Copa ampla", "Roxo");

        // then
        assertEquals(3, mapa.getTotalArvores());
        assertEquals(2, mapa.getObjetosCriados());
        assertTrue(capturarSaida(mapa::exibirResumo).endsWith("Objetos compartilhados/reutilizados: 1"));

    }

    /**
     * Verifica as validações de entrada.
     */
    @Test
    void deveRejeitarEntradasInvalidas() {

        MapaParque mapa = new MapaParque();

        assertThrows(IllegalArgumentException.class, () -> mapa.cadastrarArvore(-1, 0, "Ipê", "Copa", "Amarelo"));
        assertThrows(IllegalArgumentException.class, () -> mapa.cadastrarArvore(0, 0, " ", "Copa", "Amarelo"));
        assertThrows(IllegalArgumentException.class, () -> mapa.cadastrarArvore(0, 0, "Ipê", null, "Amarelo"));
        assertEquals(0, mapa.getTotalArvores());

    }

    /**
     * Verifica a saída completa de {@link App#main(String[])}.
     */
    @Test
    void deveExecutarDemonstracaoCompleta() {

        // when
        String saida = capturarSaida(() -> App.main(new String[0]));

        // then
        assertEquals("""
                ========================================
                SISTEMA DE MAPA DO PARQUE
                ========================================

                CADASTRANDO ÁRVORES...

                [NOVA ÁRVORE] Espécie: Ipê-amarelo | Formato: Copa arredondada | Cor: Amarelo
                [NOVA ÁRVORE] Espécie: Jacarandá | Formato: Copa ampla | Cor: Roxo
                [REUTILIZANDO] Espécie: Ipê-amarelo | Formato: Copa arredondada | Cor: Amarelo
                [NOVA ÁRVORE] Espécie: Eucalipto | Formato: Copa estreita | Cor: Verde
                [REUTILIZANDO] Espécie: Jacarandá | Formato: Copa ampla | Cor: Roxo
                [REUTILIZANDO] Espécie: Ipê-amarelo | Formato: Copa arredondada | Cor: Amarelo

                ----------------------------------------
                ÁRVORES REGISTRADAS NO MAPA
                ----------------------------------------
                Árvore 1:
                Espécie: Ipê-amarelo
                Posição: (10, 20)

                Árvore 2:
                Espécie: Jacarandá
                Posição: (15, 30)

                Árvore 3:
                Espécie: Ipê-amarelo
                Posição: (25, 40)

                Árvore 4:
                Espécie: Eucalipto
                Posição: (35, 50)

                Árvore 5:
                Espécie: Jacarandá
                Posição: (45, 60)

                Árvore 6:
                Espécie: Ipê-amarelo
                Posição: (55, 70)

                ----------------------------------------
                RESUMO DO MAPA
                ----------------------------------------

                Total de árvores no mapa: 6
                Objetos de árvores criados: 3
                Objetos compartilhados/reutilizados: 3

                ========================================""", saida);

    }
}
